package com.iwcn.master.repositories;

import com.iwcn.master.entities.Producto;

public interface DataRepository {
	
	public void addProduct(String name,int reference,int price,int stock);
	
	public Producto[] removeProduct(int id);
	
	public Producto getProduct(int id);
	
	public Producto[] getAll();
	
	public void editProduct(String name,int reference,int price,int stock,int id);
	
	
	
	

}
