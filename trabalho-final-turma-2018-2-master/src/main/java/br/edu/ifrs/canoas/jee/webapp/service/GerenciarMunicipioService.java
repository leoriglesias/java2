package br.edu.ifrs.canoas.jee.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.jee.webapp.model.dao.MunicipioDAO;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Municipio;

import javax.faces.application.FacesMessage;
import br.edu.ifrs.canoas.jee.webapp.util.Mensagens;

@Stateless
public class GerenciarMunicipioService {
	
	@Inject
	private MunicipioDAO municipioDAO;

	public boolean salvaMunicipio(Municipio municipio) {
		if (municipio.getId() == null) {
			municipioDAO.insere(municipio);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "municipio.cadastro.sucesso", municipio.getNome());
			return true;
		} else {
			municipioDAO.atualiza(municipio);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "municipio.atualizado.sucesso", municipio.getNome());
			return true;
		}
	}
	
	public List<Municipio> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return municipioDAO.buscaPorCriterio(criterio);
		else
			return municipioDAO.lista();
	}

}
