package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.PaisDAO;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Pais;

import javax.faces.application.FacesMessage;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarPaisService {
	
	@Inject
	private PaisDAO paisDAO;
	
	public List<Pais> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return paisDAO.buscaPorCriterio(criterio);
		else
			return paisDAO.lista();
	}

}
