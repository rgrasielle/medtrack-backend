package com.projetoestoque.Estoque.dto;

import java.time.LocalDate;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.enums.Category;

public record ProductDTO(
		Long id,
	    String name,
	    String category,
	    Integer total,
	    Integer quantityPerDay,
	    LocalDate start,
	    LocalDate endDate,
	    Integer daysBeforeNotification,
	    Long daysRemaining
	) {
	    public ProductDTO(Product product) {
	        this(
	            product.getId(),
	            product.getName(),
	            product.getCategory().name(), // Converte enum para String
	            product.getTotal(),
	            product.getQuantityPerDay(),
	            product.getStart(),
	            product.getEndDate(), 
	            product.getDaysBeforeNotification(),
	            product.getDaysRemaining()
	        );
	    }
}
