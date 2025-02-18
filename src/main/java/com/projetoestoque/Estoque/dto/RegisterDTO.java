package com.projetoestoque.Estoque.dto;

import com.projetoestoque.Estoque.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
