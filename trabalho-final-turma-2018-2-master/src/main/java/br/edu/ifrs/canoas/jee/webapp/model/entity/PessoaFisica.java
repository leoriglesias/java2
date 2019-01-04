package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;


import lombok.Data;

/**
 * Entity implementation class for Entity: PessoaJuridica
 *
 */
@Entity
@Data
public class PessoaFisica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String cpf;
	@Pattern(regexp="^[0-9]{10}$", message="{Pessoa.rg.erro}")
	private String rg;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@ManyToMany (mappedBy="hospedes")
    private Collection<Diaria> diarias;
	public PessoaFisica() {
		super();
	}
	public PessoaFisica(String nome, String email, String telefone, Endereco endereco) {
		super(nome, email, telefone, endereco);
	}

}
