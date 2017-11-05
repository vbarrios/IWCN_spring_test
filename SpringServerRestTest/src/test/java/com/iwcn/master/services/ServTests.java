package com.iwcn.master.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

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
	
	private Producto p;
	
	private ArrayList<Producto> allProducts = new ArrayList<Producto> ();
	
	@Mock
	private ProductoRepository productoRepository;
	
	@InjectMocks
	private DatabaseLoader dataBaseServices = new DatabaseLoader();
	
	@Before
	public void init(){
		p = new Producto("p1",1,1,1);
		allProducts.add(p);
		when(productoRepository.selectAll()).thenReturn(allProducts);
		when(productoRepository.findById(anyInt())).thenReturn(p);
				
	}
	
	@Test
	public void getProductTest()  throws ServiceException{
		ArrayList<Producto>allProducts = dataBaseServices.getProduct(int id);
		assertEquals(allProducts.size(), 1);
		verify(productoRepository).selectAll();
		imprimir("selectAll ok");
	}
	
	private static void imprimir(String text) {
		System.out.println(text);
	}

}
