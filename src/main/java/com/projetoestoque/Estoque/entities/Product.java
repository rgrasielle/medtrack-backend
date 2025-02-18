package com.projetoestoque.Estoque.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.projetoestoque.Estoque.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private Integer total;
	private Integer quantityPerDay;
	private Date start;
	
	public Product() {
	}

	public Product(Long id, String name, Category category, Integer total, Integer quantityPerDay, Date start) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.total = total;
		this.quantityPerDay = quantityPerDay;
		this.start = start;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}	
}
