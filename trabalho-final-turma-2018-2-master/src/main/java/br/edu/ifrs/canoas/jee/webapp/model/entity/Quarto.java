package br.edu.ifrs.canoas.jee.webapp.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: Quarto
 *
 */
@Entity
@Data
@Builder
@AllArgsConstructor
public class Quarto extends BaseEntity<Long> implements Serializable {

	
	private static final long serialVersionUID = 57657643543453426L;
	@Size(min=3, max=20, message="{quarto.erro.numero}")
	private String numero;
	@Enumerated(EnumType.STRING)
	private TipoDeQuarto tipo;
	@Enumerated(EnumType.STRING)
	private SituacaoQuarto situacao;
	@Size(max=400, message="{quarto.erro.descricao}")
	private String descricao;
	
	public Quarto() {
		super();
		this.situacao = SituacaoQuarto.DISPONIVEL;
	}
   
}
