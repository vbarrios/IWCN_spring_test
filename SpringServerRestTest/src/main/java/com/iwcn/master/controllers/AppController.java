package com.iwcn.master.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwcn.master.entities.Producto;
import com.iwcn.master.services.DatabaseLoader;


@RestController
public class AppController {
    
	@Autowired
	private DatabaseLoader databaseLoader;
	
    @RequestMapping(value = "/ListaDeProductos", method = RequestMethod.GET)
    public List<Producto> listaDeProductos() {
        return this.databaseLoader.getAll();
    }
    
    
    @RequestMapping(value = "/AñadirProducto", method = RequestMethod.POST)
    public List<Producto> añadirProducto(@RequestBody Producto p) {
    	this.databaseLoader.addProduct(p.getName(),p.getReference(),p.getPrice(),p.getStock()); 
    	return this.databaseLoader.getAll();
    }
    
    @RequestMapping(value = "/BorrarProducto/{id}", method = RequestMethod.GET)
    public List<Producto> borrarProducto(@PathVariable int id) {
    	this.databaseLoader.removeProduct(id);
        return this.databaseLoader.getAll();
    }
    

    @RequestMapping(value = "/MostrarProducto/{id}", method = RequestMethod.GET)
    public Producto mostrarProducto(@PathVariable int id) {
    	return this.databaseLoader.getProduct(id);
    }
    
   

    @RequestMapping(value = "/EditarExProducto", method = RequestMethod.POST)
    public void editProductEx(@RequestBody Producto p){
       	this.databaseLoader.editProduct(p.getName(),p.getReference(),p.getPrice(),p.getStock(),p.getId());
       	return;
    }
    
   
}
