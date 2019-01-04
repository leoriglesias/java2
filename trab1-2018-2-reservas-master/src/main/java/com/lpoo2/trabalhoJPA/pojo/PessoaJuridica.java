package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;

/**
 * Entity implementation class for Entity: PessoaJuridica
 *
 */
@Entity
@Data
public class PessoaJuridica extends Pessoa implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String razaoSocial;
	private String cnpj;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	
	public PessoaJuridica() {
		super();
	}
   
}
