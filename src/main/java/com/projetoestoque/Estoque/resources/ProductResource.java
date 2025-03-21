package com.projetoestoque.Estoque.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.projetoestoque.Estoque.dto.ProductDTO;
import com.projetoestoque.Estoque.entities.Product;
import com.projetoestoque.Estoque.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource { 
	
	@Autowired
	private ProductService service;
	
	// Endpoint para acessar os produtos
	
	@GetMapping
    public ResponseEntity<List<ProductDTO>> findAllByUser() {
        List<Product> list = service.findAllByUser();

        // Convertendo List<Product> para List<ProductDTO>
        List<ProductDTO> dtoList = list.stream().map(ProductDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(dtoList);
    }
	
	// Endpoint para inserir produtos
	
	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	// Endpoint para deletar produtos
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	    service.delete(id);
	    return ResponseEntity.noContent().build(); 
	}
	
	// Endpoint para atualizar produtos
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product obj) {
		System.out.println("Recebido: " + obj);
	    obj = service.update(id, obj);
	    return ResponseEntity.ok().body(obj);
	}

}

	
	
	
	
	

