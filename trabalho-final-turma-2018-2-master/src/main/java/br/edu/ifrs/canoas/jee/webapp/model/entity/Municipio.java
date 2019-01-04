package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entity implementation class for Entity: Municipio
 *
 */
@Entity
@Data
public class Municipio extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Estado estado;

	private String nome;
	
	public Municipio() {
		super();
		estado = new Estado();
	}

}
