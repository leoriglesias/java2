package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Estado;

public class EstadoDAO extends BaseDAO<Estado, Long> {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Estado> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM Estado u "
		         + "WHERE lower(u.nome) = :nome "
		         + " or lower (u.id) = :id "
		         + " or lower (u.pais_id) = :pais_id ")
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .setParameter("id", criterio.trim().toLowerCase())
		         .setParameter("pais_id", criterio.trim().toLowerCase())
		         .getResultList();
	}

}
