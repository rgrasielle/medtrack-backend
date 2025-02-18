package com.projetoestoque.Estoque.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoestoque.Estoque.dto.AuthenticationDTO;
import com.projetoestoque.Estoque.dto.LoginResponseDTO;
import com.projetoestoque.Estoque.dto.RegisterDTO;
import com.projetoestoque.Estoque.entities.User;
import com.projetoestoque.Estoque.services.TokenService;
import com.projetoestoque.Estoque.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(value = "/auth")
public class Authentication {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Autowired 
    private UserService userService;
    
    @Autowired
    private TokenService tokenService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        try {
            User newUser = userService.register(data);
            URI uri = URI.create("/auth/" + newUser.getId());
            return ResponseEntity.created(uri).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
}
