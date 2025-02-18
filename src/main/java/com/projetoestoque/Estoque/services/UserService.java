package com.projetoestoque.Estoque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoestoque.Estoque.dto.RegisterDTO;
import com.projetoestoque.Estoque.dto.UserDTO;
import com.projetoestoque.Estoque.entities.User;
import com.projetoestoque.Estoque.repositories.UserRepository;
import com.projetoestoque.Estoque.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	// Listar usuários
	public List<User> findAll() {
		return repository.findAll();
	}

	// Registrar usuário
    public User register(RegisterDTO data) {
        if (repository.findByEmail(data.email()) != null) {
            throw new IllegalArgumentException("Erro: Este email já está cadastrado!");
        }

        String hashedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.email(), hashedPassword, data.role());
        return repository.save(newUser);
    }

	// Deletar
	public void delete(Long id) {
	    if (!repository.existsById(id)) {
	        throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
	    }
	    repository.deleteById(id);
	}

	// Atualizar
	public User update(Long id, User obj) {
		User newObj = repository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
	}
	

	// Verificar se o email existe
	public boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }
}
