package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "diaria_tipo")
@Data
public class Diaria  extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = -4408735553657743241L;

	@NotNull
	@Temporal(TemporalType.DATE)
	protected Date data = new Date();
	
	@NotNull
	private Integer qtdDias;
	
	@ManyToMany()
	protected Collection<PessoaFisica> hospedes;
	
	@ManyToOne
	protected Quarto quarto;
   
	public Diaria() {
		super();
		this.hospedes = new ArrayList<>();
		this.quarto = new Quarto();
	}
	public Diaria(Date data, Quarto quarto, Integer dias) {
		this.data = data;
		this.hospedes = new ArrayList<>();
		this.quarto = quarto;
		this.qtdDias = dias;
	}
	public void addHospedes(PessoaFisica pessoa) {
		this.hospedes.add(pessoa);
	}

}
