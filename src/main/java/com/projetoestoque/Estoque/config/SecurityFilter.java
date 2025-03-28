package com.projetoestoque.Estoque.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projetoestoque.Estoque.repositories.UserRepository;
import com.projetoestoque.Estoque.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;

	// Recupera as informações do token
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    
	    String token = recoverToken(request);

	    if (token != null) {
	        String email = tokenService.validateToken(token);

	        if (email != null) { // Verifica se o token é válido
	            UserDetails user = userRepository.findByEmail(email);

	            if (user != null) { // Verifica se o usuário existe
	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	                
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	            }
	        }
	    }

	    filterChain.doFilter(request, response); // Chama o próximo filtro
	}

	
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null) {
			return null;
		}
		return authHeader.replace("Bearer ", "");
	}
}
