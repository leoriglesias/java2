package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.EstadoDAO;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Estado;

import javax.faces.application.FacesMessage;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarEstadoService {
	
	@Inject
	private EstadoDAO estadoDAO;

	public boolean salvaEstado(Estado estado) {
		if (estado.getId() == null) {
			estadoDAO.insere(estado);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "estado.cadastro.sucesso", estado.getNome());
			return true;
		} else {
			estadoDAO.atualiza(estado);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "estado.atualizado.sucesso", estado.getNome());
			return true;
		}
	}
	
	public List<Estado> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return estadoDAO.buscaPorCriterio(criterio);
		else
			return estadoDAO.lista();
	}

}
