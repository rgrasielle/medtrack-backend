package com.projetoestoque.Estoque.dto;

import com.projetoestoque.Estoque.entities.User;

public class UserDTO {
    private Long id;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

