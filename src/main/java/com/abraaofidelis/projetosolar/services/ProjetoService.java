package com.abraaofidelis.projetosolar.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abraaofidelis.projetosolar.models.Cliente;
import com.abraaofidelis.projetosolar.models.Projeto;
import com.abraaofidelis.projetosolar.repositories.ProjetoRepository;

@Service
public class ProjetoService {
	
	@Autowired
	private ProjetoRepository projetoRepository;
	
	private ClienteService clienteService;

	public Projeto findById(Long id) {
		Optional<Projeto> projeto = this.projetoRepository.findById(id);
		return projeto.orElseThrow(()-> new RuntimeException(
				"Tarefa não encontrada! Id: " + id));
	}
	
	@Transactional
	public Projeto create(Projeto obj) {
		Cliente cliente = this.clienteService.findById(obj.getCliente().getId());
		obj.setId(null);
		obj.setCliente(cliente);
		obj = this.projetoRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Projeto update(Projeto obj) {
		Projeto newObj = this.findById(obj.getId());
		newObj.setConsumoMedio(obj.getConsumoMedio());
		newObj.setIrradianciaSolar(obj.getIrradianciaSolar());
		newObj.setTipoLigacao(obj.getTipoLigacao());
		newObj.setPercentualDePerdas(obj.getPercentualDePerdas());
		
		return this.projetoRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.projetoRepository.deleteById(id);
		}catch(Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
}
