package com.projetoestoque.Estoque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.entities.User;
import com.projetoestoque.Estoque.repositories.ProductRepository;
import com.projetoestoque.Estoque.repositories.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	// Método para retornar todos os produtos do usuário autenticado

	public List<Product> findAllByUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);  // Recupera o usuário autenticado

			if (user != null) {
				return productRepository.findByUser(user);  // Retorna os produtos do usuário
			}
		}
		return List.of(); // Retorna uma lista vazia se não encontrar o usuário
	}

	// Método para inserir produtos

	public Product insert(Product obj) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String email = ((UserDetails) principal).getUsername();
			User user = userRepository.findByEmail(email);

			if (user != null) {
				obj.setUser(user); // Associa o usuário ao produto
			}
		}

		return productRepository.save(obj);
	}

	// Método para deletar produtos
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	// Método para atualizar produtos
	public Product update(Long id, Product obj) {
		Product newObj = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		updateData(newObj, obj);
		return productRepository.save(newObj);
	}

	private void updateData(Product newObj, Product obj) {
		newObj.setName(obj.getName());
		newObj.setCategory(obj.getCategory());
		newObj.setTotal(obj.getTotal());
		newObj.setQuantityPerDay(obj.getQuantityPerDay());
		newObj.setStart(obj.getStart());
	}
}
