package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entity implementation class for Entity: Reserva
 *
 */
@Entity
@Data
public class Reserva implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date data;
	private Double valor;
	
	public Reserva() {
		super();
	}

	public Reserva(Date data, Double valor) {
		this.data = data;
		this.valor = valor;
	}
   
}
