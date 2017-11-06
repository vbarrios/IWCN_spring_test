package com.iwcn.master.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iwcn.master.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

		Producto findById(int id);
	
	@Query(value = "SELECT * FROM PRODUCTO", nativeQuery = true)  
	List<Producto> selectAll();

}
