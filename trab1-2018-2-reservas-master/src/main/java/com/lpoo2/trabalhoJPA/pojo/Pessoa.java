package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */
@Entity
@Data
public abstract class Pessoa implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String nome;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	private String telefone;
	private Endereco endereco;
	
	public Pessoa() {
		super();
	}
	public Pessoa(String nome, String email, String telefone, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
}
