package com.abraaofidelis.projetosolar.controllers;

import java.net.URI;
import java.util.List;

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

import com.abraaofidelis.projetosolar.models.Projeto;
import com.abraaofidelis.projetosolar.models.Projeto.CreateProjeto;
import com.abraaofidelis.projetosolar.models.Projeto.UpdateProjeto;
import com.abraaofidelis.projetosolar.services.ProjetoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoService projetoService;
	
	//localhost:8080/projeto/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> findById(@PathVariable Long id){
		Projeto obj = this.projetoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//localhost:8080/projeto/ --> deve enviar no body o objeto também
	@PostMapping
	@Validated(CreateProjeto.class)
	public ResponseEntity<Void> create(@Valid @RequestBody Projeto obj){
		this.projetoService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//localhost:8080/projeto/{id} --> deve enviar no body o objeto também
	@PutMapping("/{id}")
	@Validated(UpdateProjeto.class)
	public ResponseEntity<Void> update(@Valid @RequestBody Projeto obj, @PathVariable Long id){
		obj.setId(id);
		this.projetoService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//localhost:8080/projeto/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.projetoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//localhost:8080/projeto/cliente/{clienteId}` --> este método tras uma lista de projetos para um id de cliente
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity <List<Projeto>> findAllByClienteId(@PathVariable Long clienteId){
		List<Projeto> objs = this.projetoService.findAllByUserId(clienteId);
		return ResponseEntity.ok().body(objs);
		}
}
