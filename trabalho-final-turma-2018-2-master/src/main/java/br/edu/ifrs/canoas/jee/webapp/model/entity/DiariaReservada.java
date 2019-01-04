package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * Entity implementation class for Entity: DiariaReservada
 *
 */
@Entity
@Data
public class DiariaReservada extends Diaria implements Serializable {

	
	private static final long serialVersionUID = 1L;
 
	@JoinColumn(unique = true)
	@OneToOne
	private Reserva reserva;
	
	public DiariaReservada() {
		super();
	}
	public DiariaReservada(Date data) {
		this.data = data;
	}
}
