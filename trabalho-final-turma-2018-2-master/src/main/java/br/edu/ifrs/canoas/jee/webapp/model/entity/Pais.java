package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;

import lombok.Data;

/**
 * Entity implementation class for Entity: Pais
 *
 */
@Entity
@Data
public class Pais extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;

	public Pais() {
		super();
	}
   
}
