package com.projetoestoque.Estoque.entities;

import java.io.Serializable;
import java.util.Date;

import com.projetoestoque.Estoque.enums.Category;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Category category;
	private Integer total;
	private Integer quantityPerDay;
	private Date start;
	
	public Product() {
	}

	public Product(String name, Category category, Integer total, Integer quantityPerDay, Date start) {
		super();
		this.name = name;
		this.category = category;
		this.total = total;
		this.quantityPerDay = quantityPerDay;
		this.start = start;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getQuantityPerDay() {
		return quantityPerDay;
	}

	public void setQuantityPerDay(Integer quantityPerDay) {
		this.quantityPerDay = quantityPerDay;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
	
}
