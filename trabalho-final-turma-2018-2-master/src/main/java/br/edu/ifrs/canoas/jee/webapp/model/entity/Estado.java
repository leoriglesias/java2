package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entity implementation class for Entity: Estado
 *
 */
@Entity
@Data
public class Estado extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Pais pais;
	
	private String nome;
	
	public Estado() {
		super();
		pais = new Pais();
	}

}
