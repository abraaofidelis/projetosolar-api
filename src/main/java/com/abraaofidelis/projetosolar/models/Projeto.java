package com.abraaofidelis.projetosolar.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = Projeto.TABLE_NAME)
public class Projeto {
	public interface CreateProjeto{}
	public interface UpdateProjeto{}

	public static final String TABLE_NAME = "projeto";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	// Consumo médio mensal dos ultimos dozes meses
	@Column(name = "cosumo_medio", nullable = false)
	@NotNull (groups = {CreateProjeto.class, UpdateProjeto.class})
	@NotEmpty (groups = {CreateProjeto.class, UpdateProjeto.class})
	private double consumoMedio;
	
	/*
	 *  Informar se a ligação residencial é Monofásica, Bifásica ou Trifásica
	 *  1 - Monofasico
	 *  2 - Bifasico
	 *  3 - Trifasico
	 */
	@Column(name = "tipo_ligacao", nullable = false)
	@NotNull (groups = {CreateProjeto.class, UpdateProjeto.class})
	@NotEmpty (groups = {CreateProjeto.class, UpdateProjeto.class})
	private int tipoLigacao;
	
	// Informar o percentual de perdas que será considerada no dimensionamento do sistema solar
	@Column(name = "perdas_totais", nullable = false)
	@NotNull (groups = {CreateProjeto.class, UpdateProjeto.class})
	@NotEmpty (groups = {CreateProjeto.class, UpdateProjeto.class})
	private double percentualDePerdas;
	
	/* 
	 * Informar o valor da irradiancia solar que será considerada, normalmente depende
	 * da localização da instalação e consultada no site CRESESB.
	 * Para os exemplos será utilizada o valor de 4.5
	*/
	@Column(name = "irradiancia_solar", nullable = false)
	@NotNull (groups = {CreateProjeto.class, UpdateProjeto.class})
	@NotEmpty (groups = {CreateProjeto.class, UpdateProjeto.class})
	private double irradianciaSolar;
	
	@Column(name = "potencia_sistema", nullable = false, precision = 2)
	private double potenciaSistema;
	
	@Column(name = "consumo_minimo", nullable = false)
	private double consumoMinimo;
	
	@Column(name = "consumo_projeto", nullable = false)
	private double consumoParaProjeto;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, updatable = false)
	private Cliente cliente;
	
	
	public Projeto() {}
	
	public Projeto(double consumoMedio, int tipoLigacao, double percentualDePerdas, double irradianciaSolar, Cliente cliente) {
		this.consumoMedio = consumoMedio;
		this.tipoLigacao = tipoLigacao;
		this.percentualDePerdas = percentualDePerdas;
		this.irradianciaSolar = irradianciaSolar;
		this.cliente = cliente;
		
		this.calculaPotencia(this.consumoMedio, this.tipoLigacao, this.percentualDePerdas, this.irradianciaSolar);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getConsumoMedio() {
		return this.consumoMedio;
	}
	
	public void setConsumoMedio(double consumoMedio) {
		this.consumoMedio = consumoMedio;
		this.calculaPotencia(this.consumoMedio, this.tipoLigacao, this.percentualDePerdas, this.irradianciaSolar);
	}
	
	public int getTipoLigacao() {
		return this.tipoLigacao;
	}
	
	public void setTipoLigacao(int tipoLigacao) {
		this.tipoLigacao = tipoLigacao;
		this.calculaPotencia(this.consumoMedio, this.tipoLigacao, this.percentualDePerdas, this.irradianciaSolar);
	}
	public double getPercentualDePerdas() {
		return this.percentualDePerdas;
	}
	
	public void setPercentualDePerdas(double percentualDePerdas) {
		this.percentualDePerdas = percentualDePerdas;
		this.calculaPotencia(this.consumoMedio, this.tipoLigacao, this.percentualDePerdas, this.irradianciaSolar);
	}
	
	public double getIrradianciaSolar() {
		return this.irradianciaSolar;
	}
	
	public void setIrradianciaSolar(double irradianciaSolar) {
		this.irradianciaSolar = irradianciaSolar;
		this.calculaPotencia(this.consumoMedio, this.tipoLigacao, this.percentualDePerdas, this.irradianciaSolar);
	}
	
	public double getConsumoMinimo() {
		return this.consumoMinimo;
	}
	
	public double getConsumoParaProjeto() {
		return this.consumoParaProjeto;
	}
	
	public double getPotenciaSistema() {
		return this.potenciaSistema;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//Método que será usado no calculo da pontencia do sistema solar
	private void calculaPotencia(double consumoMedio, int tipoLigacao, double percentualDePerdas, double irradianciaSolar) {
		//Verifica o tipo de ligaçao para calcular o tanto de consumo que sera considerado no projeto
		if (tipoLigacao == 1) {
			this.consumoMinimo = 25.0;
			this.consumoParaProjeto = consumoMedio - this.consumoMinimo;
		}else if(tipoLigacao == 1){
			this.consumoMinimo = 50.0;
			this.consumoParaProjeto = consumoMedio - this.consumoMinimo;
		}else {
			this.consumoMinimo = 100.0;
			this.consumoParaProjeto = consumoMedio - this.consumoMinimo;
		}
		
		this.potenciaSistema = this.consumoParaProjeto/(30 * irradianciaSolar * (1 - percentualDePerdas));
	}
	
	
	//Equals
	@Override
	public boolean equals(Object obj) {
	    if (obj == this)
	        return true;
	    if (obj == null)
	    	return false;
	    if (!(obj instanceof Projeto))
	        return false;
	    Projeto other = (Projeto) obj;
	    if(this.id == null)
	    	if (other.id != null)
	    		return false;
	    	else if (!this.id.equals(other.id))
	    		return false;
	    return Objects.equals(this.id,  other.id) && Objects.equals(this.consumoMedio,  other.consumoMedio)
	    		&& Objects.equals(this.tipoLigacao,  other.tipoLigacao)
	    		&& Objects.equals(this.percentualDePerdas,  other.percentualDePerdas)
	    		&& Objects.equals(this.irradianciaSolar,  other.irradianciaSolar);
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
