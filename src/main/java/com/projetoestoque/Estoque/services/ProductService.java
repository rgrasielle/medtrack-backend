package com.projetoestoque.Estoque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	// Método para retornar todos os produtos
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	// Método para inserir produtos
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	

	
	

}
