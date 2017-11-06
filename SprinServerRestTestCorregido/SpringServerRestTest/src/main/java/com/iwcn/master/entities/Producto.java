package com.iwcn.master.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public String name;
	public int reference;
	public int price;
	public int stock;
	public Producto(String name,int reference,int price,int stock)
	{
		this.name=name;
		this.reference=reference;
		this.price=price;
		this.stock=stock;
	}
	
    public Producto () {
    	    }
}
