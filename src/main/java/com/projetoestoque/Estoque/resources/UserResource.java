package com.projetoestoque.Estoque.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoestoque.Estoque.dto.UserDTO;
import com.projetoestoque.Estoque.entities.User;
import com.projetoestoque.Estoque.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource { 
	
	@Autowired
	private UserService service;
	
	// Endpoint para listar os usuários
	@GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

	// Endpoint para deletar usuário por ID
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
	
	// Endpoint para atualizar usuários
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User obj) {
        obj.setId(id);
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
	
	// Endpoint para verificar se o email já existe
	@GetMapping("/email-exists")
	public ResponseEntity<Map<String, Boolean>> checkIfEmailExists(@RequestParam String email) {
	    boolean exists = service.emailExists(email); 
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", exists);
	    return ResponseEntity.ok(response);
	}
	
	// Método para retornar as informações do usuário autenticado
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User principal) {
        // Busca o usuário a partir do principal (que é o usuário autenticado)
        User user = service.findByEmail(principal.getUsername());  // ou principal.getName()
        return ResponseEntity.ok(user);
    }
	

}
 
	
	

	
	

