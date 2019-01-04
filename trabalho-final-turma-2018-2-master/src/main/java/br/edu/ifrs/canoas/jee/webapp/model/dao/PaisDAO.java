package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Pais;

public class PaisDAO extends BaseDAO<Pais, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Pais> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM Pais u "
		         + "WHERE lower(u.nome) = :nome ")
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .getResultList();
	}

}
