package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


import lombok.Data;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */
@Entity
@Data
public abstract class Pessoa extends BaseEntity<Long> implements Serializable {


	private static final long serialVersionUID = 1L;

	@Pattern(regexp="(\\w.+\\s).+", message="{Pessoa.nome.erro}")
	@Size(max = 100)
	private String nome;
	@Email
	@Pattern(regexp=".+@.+\\..+", message="{Pessoa.email.erro}")
	private String email;
	private String telefone;
	private Endereco endereco;

	public Pessoa() {
		super();
	}
	public Pessoa(String nome, String email, String telefone, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public String getCpfCnpj() {
		return (this instanceof PessoaFisica) ? ((PessoaFisica)this).getCpf(): ((PessoaJuridica)this).getCnpj();
	}

}
