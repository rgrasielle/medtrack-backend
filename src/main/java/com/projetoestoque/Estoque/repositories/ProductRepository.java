package com.projetoestoque.Estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoestoque.Estoque.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
