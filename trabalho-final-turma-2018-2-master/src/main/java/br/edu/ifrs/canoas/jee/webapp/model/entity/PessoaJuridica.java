package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Entity implementation class for Entity: PessoaJuridica
 *
 */
@Entity
@Data
public class PessoaJuridica extends Pessoa implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Pattern(regexp="(\\w.+\\s).+", message="Razao social invalida")
	@Size(max = 100)
	private String razaoSocial;
	@Column(unique=true)
	private String cnpj;
	@Size(max = 30)
	private String inscricaoEstadual;
	@Size(max = 30)
	private String inscricaoMunicipal;
	
	public PessoaJuridica() {
		super();
	}
   
}
