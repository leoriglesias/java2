package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entity implementation class for Entity: DiariaAvulsa
 *
 */
@Entity
@Data
public class DiariaAvulsa extends Diaria implements Serializable {

	
	private static final long serialVersionUID = 1L;
 
	public DiariaAvulsa() {
		super();
	}
	public DiariaAvulsa(Date data, Pessoa pessoa) {
		this.data = data;
		this.addHospedes((PessoaFisica) pessoa); 
	}
	public DiariaAvulsa(Date data) {
		this.data = data;
	}
	
   
}
