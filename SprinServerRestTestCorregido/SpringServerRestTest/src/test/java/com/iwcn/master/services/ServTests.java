package com.iwcn.master.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import com.iwcn.master.services.DatabaseLoader;
import com.iwcn.master.entities.Producto;
import com.iwcn.master.repositories.ProductoRepository;

@RunWith(MockitoJUnitRunner.class) public class ServTests {
	
	private Producto p1;
	
	private ArrayList<Producto> allProducts = new ArrayList<Producto> ();
	
	@Mock
	private ProductoRepository productoRepository;
	
	@InjectMocks
	private DatabaseLoader dataBaseServices = new DatabaseLoader();
	
	@Before
	public void init(){
		p1 = new Producto("p1",1,1,1);
		allProducts.add(p1);
		Producto p2 = new Producto("p2",2,2,2);
		allProducts.add(p2);
		when(productoRepository.selectAll()).thenReturn(allProducts);
		when(productoRepository.findById(1)).thenReturn(p1);
		when(productoRepository.findById(2)).thenReturn(p2);
	}
	
	@Test
	public void getProductsTest()  throws ServiceException{
		ArrayList<Producto>allProducts = dataBaseServices.getAll();
		assertEquals(allProducts.size(), 2);
		verify(productoRepository).selectAll();
		imprimir("selectAll ok");
	}
	
	@Test
	public void getProductTest() throws ServiceException {

		Producto p = dataBaseServices.getProduct(2);
		assertEquals(p.getName(), "p2");
		assertEquals(p.getReference(), 2);
		assertEquals(p.getPrice(), 2);
		assertEquals(p.getStock(), 2);
		verify(productoRepository).findById(2);
		imprimir("findById ok");
	}
	
	@Test
	public void addProductTest() throws ServiceException{

		assertEquals(dataBaseServices.getAll().size(), 2);
        Producto p3 = new Producto ("p3",3,3,3);
        when(productoRepository.findById(3)).thenReturn(p3);
        dataBaseServices.addProduct("p3",3,3,3);
        allProducts.add(p3);
        assertEquals(dataBaseServices.getAll().size(), 3);
        imprimir("Nombre de producto:" + this.dataBaseServices.getProduct(3).getName());
        
	}
	
	@Test
	public void removeProductTest() throws ServiceException{

        assertEquals(dataBaseServices.getAll().size(), 2);
        dataBaseServices.removeProduct(1);
        allProducts.remove(p1);
        assertEquals(dataBaseServices.getAll().size(), 1);
        verify(productoRepository).delete(dataBaseServices.getProduct(1));
		imprimir("remove ok");
	}

	
	@Test
	public void editProductTest() throws ServiceException{
        
        assertEquals(dataBaseServices.getAll().size(), 2);
        Producto p2 = dataBaseServices.getProduct(2);
        dataBaseServices.editProduct("p3",3,3,3,2);
        assertEquals(dataBaseServices.getAll().size(), 2);
        verify(productoRepository).save(p2);
		imprimir("editProduct ok");
	}

	
	private static void imprimir(String text) {
		System.out.println(text);
	}

}
