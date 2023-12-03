package com.abraaofidelis.projetosolar.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abraaofidelis.projetosolar.models.Cliente;
import com.abraaofidelis.projetosolar.repositories.ClienteRepository;
import com.abraaofidelis.projetosolar.repositories.ProjetoRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new RuntimeException(
				"Cliente não encontrado! Id: " + id));
	}
	
	@Transactional
	public Cliente create(Cliente obj) {
		obj.setId(null);
		obj = this.clienteRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());
		newObj.setBairro(obj.getBairro());
		newObj.setCidade(obj.getCidade());
		newObj.setComplemento(obj.getComplemento());
		newObj.setCep(obj.getCep());
		newObj.setCelular(obj.getCelular());
		newObj.setUf(obj.getUf());
		
		return this.clienteRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.clienteRepository.deleteById(id);
		}catch(Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
}
