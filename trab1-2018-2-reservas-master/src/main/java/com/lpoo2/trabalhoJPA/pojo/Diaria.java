package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.function.IntPredicate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import lombok.Data;

/**
 * Entity implementation class for Entity: Diaria
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "diaria_tipo")
@Data
public abstract class Diaria implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected Date data;
	@ManyToMany()
	protected Collection<PessoaFisica> hospedes;
   
	public Diaria() {
		super();
		this.hospedes = new ArrayList<>();
	}
	public Diaria(Date data) {
		this.data = data;
		this.hospedes = new ArrayList<>();
	}
	public void addHospedes(PessoaFisica pessoa) {
		this.hospedes.add(pessoa);
		
	}

}
