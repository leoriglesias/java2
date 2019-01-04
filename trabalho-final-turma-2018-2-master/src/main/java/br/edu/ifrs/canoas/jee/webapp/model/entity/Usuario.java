package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
@Data
public class Usuario extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 3747992687232892283L;

	@NotNull @Email 
	private String email;
	
	@NotNull
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message="SENHA: mínimo 8 caracteres, obrigatoriamente com minúsculas, maiúsculas, letras e números")
	private String senha;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z0-9_]+$", message="NOME: Máximo: 1 nome")
	private String nome;

	@NotNull
	@Pattern(regexp="(\\w.+\\s).+", message="SOBRENOME: Mínimo: 2 nomes")
	private String sobrenome;
	
	@OneToOne
	private Municipio municipio;

	@NotNull
	@Pattern(regexp="^[0-9A-Za-z\\s]+$", message="LOGRADOURO: Deve possuir letras e números")
	private String logradouro;
	
	public Usuario() {
		super();
		municipio = new Municipio();
	}

}
