package com.projetoestoque.Estoque.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.enums.Category;

@RestController
@RequestMapping(value = "/products")
public class ProductResources { 
	
	
	// Endpoint para acessar os produtos
	@GetMapping
	public ResponseEntity<Product> findAll() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		Product p = null;
		try {
			p = new Product("Ginseg", Category.SUPLEMENTO, 90, 1, sdf.parse("18/02/2025"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(p);
	}
	
	
	
	

}
