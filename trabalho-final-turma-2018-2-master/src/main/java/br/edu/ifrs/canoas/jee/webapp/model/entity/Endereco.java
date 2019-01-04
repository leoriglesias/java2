package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entity implementation class for Entity: Endereco
 *
 */
@Entity
@Data
public class Endereco implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String uf;
	
	public Endereco() {
		super();
	}
   
}
