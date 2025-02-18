package com.projetoestoque.Estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoestoque.Estoque.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
