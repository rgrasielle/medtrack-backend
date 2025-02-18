package com.projetoestoque.Estoque.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.enums.Category;
import com.projetoestoque.Estoque.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Product p1 = new Product(null, "Ginseng", Category.SUPLEMENTO, 90, 1, sdf.parse("18/02/2025"));
		Product p2 = new Product(null, "Vitamina D", Category.SUPLEMENTO, 120, 1, sdf.parse("16/02/2025"));
		
		productRepository.saveAll(Arrays.asList(p1, p2));
		
	}

}
