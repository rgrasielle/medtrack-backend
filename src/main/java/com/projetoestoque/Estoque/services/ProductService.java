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
	
	// Método para deletar produtos
	public void delete(Long id) {
	    repository.deleteById(id);
	}
	
	// Método para atualizar produtos
	public Product update(Long id, Product obj) {
		Product newObj = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(Product newObj, Product obj) {
		newObj.setName(obj.getName());
		newObj.setCategory(obj.getCategory());
		newObj.setTotal(obj.getTotal());
		newObj.setQuantityPerDay(obj.getQuantityPerDay());
		newObj.setStart(obj.getStart());
	}
}
