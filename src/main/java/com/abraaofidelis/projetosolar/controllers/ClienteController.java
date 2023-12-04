package com.abraaofidelis.projetosolar.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abraaofidelis.projetosolar.models.Cliente;
import com.abraaofidelis.projetosolar.models.Cliente.CreateCliente;
import com.abraaofidelis.projetosolar.models.Cliente.UpdateCliente;
import com.abraaofidelis.projetosolar.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@Validated
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//localhost:8080/cliente/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		Cliente obj = this.clienteService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Validated(CreateCliente.class)
	public ResponseEntity<Void> create(@Valid @RequestBody Cliente obj){
		this.clienteService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Validated(UpdateCliente.class)
	public ResponseEntity<Void> update(@Valid @RequestBody Cliente obj, @PathVariable Long id){
		obj.setId(id);
		this.clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
