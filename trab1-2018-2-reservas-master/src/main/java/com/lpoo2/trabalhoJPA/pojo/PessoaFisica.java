package com.lpoo2.trabalhoJPA.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

import lombok.Data;

/**
 * Entity implementation class for Entity: PessoaJuridica
 *
 */
@Entity
@Data
public class PessoaFisica extends Pessoa implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private String rg;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (sexo != other.sexo)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}
   
}
