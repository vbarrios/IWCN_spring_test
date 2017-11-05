package com.iwcn.master.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iwcn.master.entities.Producto;
import com.iwcn.master.repositories.DataRepository;


@Service
public class DatabaseLoader implements DataRepository {
	
	   
	@Override    
    public void addProduct(String name,int reference,int price,int stock)
    {
		String url = "http://localhost:8080/AñadirProducto";
		RestTemplate restTemplate = new RestTemplate();
		Producto product = new Producto(name,reference,price,stock);
		restTemplate.postForObject(url, product, String.class);
    }
  
	@Override
    public Producto[] removeProduct(int id)
    {
		String url = "http://localhost:8080/BorrarProducto/" + id;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Producto[].class);
    }
  
	@Override
    public Producto getProduct(int id)
    {
		String url = "http://localhost:8080/MostrarProducto/" + id;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url , Producto.class);
    }
   
	@Override
    public Producto[] getAll(){
		String url = "http://localhost:8080/ListaDeProductos";
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, Producto[].class);
    }
    
	@Override
    public void editProduct(String name,int reference,int price,int stock,int id) {
		String url = "http://localhost:8080/EditarExProducto";
		RestTemplate restTemplate = new RestTemplate();
		Producto[] productList = this.getAll();
		int Index = 0;
		
		for(int i = 0; i > productList.length; i++) {
			
			if(productList[i].getId() == id) {
				Index = i; break;
			}
		}
		productList[Index].setName(name);
		productList[Index].setReference(reference);
		productList[Index].setPrice(price);
		productList[Index].setStock(stock);
		
		restTemplate.postForObject(url, productList[Index], String.class);
    }
    

}
