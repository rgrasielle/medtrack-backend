package com.projetoestoque.Estoque.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetoestoque.Estoque.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start;

    private LocalDate endDate;

    @Column(nullable = false)
    private Integer daysBeforeNotification;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore  // Evita referência cíclica no JSON
    private User user;

    public Product() {
    }

    public Product(Long id, String name, Category category, Integer total, Integer quantityPerDay, LocalDate start,
                   User user, Integer daysBeforeNotification) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.total = total;
        this.quantityPerDay = quantityPerDay;
        this.start = start;
        this.user = user;
        this.daysBeforeNotification = daysBeforeNotification;
        this.endDate = calcularDataTermino();  // Calcula a data de término
    }

    // Getters e Setters
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
        atualizarDataTermino();  // Atualiza a data de término ao modificar total
    }

    public Integer getQuantityPerDay() {
        return quantityPerDay;
    }

    public void setQuantityPerDay(Integer quantityPerDay) {
        this.quantityPerDay = quantityPerDay;
        atualizarDataTermino();  // Atualiza a data de término ao modificar a quantidade por dia
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
        atualizarDataTermino();  // Atualiza a data de término ao modificar a data de início
    }

    public Integer getDaysBeforeNotification() {
        return daysBeforeNotification;
    }

    public void setDaysBeforeNotification(Integer daysBeforeNotification) {
        this.daysBeforeNotification = daysBeforeNotification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(id, other.id);
    }

    // Método para calcular a data de término
    public LocalDate calcularDataTermino() {
        if (start == null || total == null || quantityPerDay == null || total <= 0 || quantityPerDay <= 0) {
            return null;  // Retorna null caso algum dado esteja inválido
        }
        int diasParaAcabar = (int) Math.ceil((double) total / quantityPerDay);
        return start.plusDays(diasParaAcabar);
    }

    // Método auxiliar para atualizar a data de término
    private void atualizarDataTermino() {
        this.endDate = calcularDataTermino();
    }
    
    // Método para calcular os dias restantes
    public long getDaysRemaining() {
        if (endDate == null) {
            return 0;  // Retorna 0 caso a data de término seja nula
        }
        long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        return daysRemaining >= 0 ? daysRemaining : 0;  // Não permitir valor negativo
    }


}
