package com.abraaofidelis.projetosolar.models;

import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente {
	
	public interface CreateCliente {}
	public interface UpdateCliente {}
	
	public static final String TABLE_NAME = "cliente";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class},min = 2, max=100)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class},min = 11, max=11)
	private String cpf;
	
	@Column(name = "celular", length = 15, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class}, min = 8, max=15)
	private String celular;
	
	@Column(name = "logradouro", length = 100, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	private String logradouro;
	
	@Column(name = "numero", nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	private Integer numero;
	
	@Column(name = "complemento", length = 100)
	private String complemento;
	
	@Column(name = "bairro", length = 50, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	private String bairro;
	
	@Column(name = "cep", length = 10, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	private String cep;
	
	@Column(name = "cidade", length = 50, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	private String cidade;
	
	@Column(name = "uf", length = 2, nullable = false)
	@NotNull(groups = {CreateCliente.class, UpdateCliente.class})
	@NotEmpty(groups = {CreateCliente.class, UpdateCliente.class})
	@Size(groups = {CreateCliente.class, UpdateCliente.class},min = 2, max=2)
	private String uf;
	
	//private List<Projeto> projetos = new ArrayList<Projeto>();
	
	public Cliente() {
		
	}
	
	public Cliente(Long id, String nome, String cpf, String celular, String logradouro, Integer numero, String complemento, String bairro, String cep, String cidade, String uf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.celular = celular;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
	}
	//=======
	// Getters and Setters
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCelular() {
		return this.celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getLogradouro() {
		return this.logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public Integer getNumero() {
		return this.numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return this.complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return this.bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCep() {
		return this.cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getCidade() {
		return this.cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getUf() {
		return this.uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	//Equals
	@Override
	public boolean equals(Object obj) {
	    if (obj == this)
	        return true;
	    if (obj == null)
	    	return false;
	    if (!(obj instanceof Cliente))
	        return false;
	    Cliente other = (Cliente) obj;
	    if(this.id == null)
	    	if (other.id != null)
	    		return false;
	    	else if (!this.id.equals(other.id))
	    		return false;
	    return Objects.equals(this.id,  other.id) && Objects.equals(this.nome,  other.nome)
	    		&& Objects.equals(this.cpf,  other.cpf);
	}
	
	// Hash Code que o Spring precisa usar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}
}
