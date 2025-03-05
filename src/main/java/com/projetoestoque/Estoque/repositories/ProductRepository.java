package com.projetoestoque.Estoque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.entities.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByUser(User user);

}
