package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.TipoDeQuarto;

public class QuartoDAO extends BaseDAO<Quarto, Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1740776420611568097L;

	@SuppressWarnings("unchecked")
	public List<Quarto> buscaPorNumeroOrdenada(Integer numero) {
		return em.createQuery("SELECT q " + "FROM Quarto q " + "WHERE q.numero = :numero ORDER BY q.numero ASC")
				.setParameter("numero", numero).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> buscaportTipo(TipoDeQuarto tipo) {
		return em.createQuery("SELECT q " + "FROM Quarto q " + "WHERE q.tipo = :tipo")
				.setParameter("tipo", tipo).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> buscaPorCriterio(String criterio) {
		return em.createQuery(
		         "SELECT q "
		         + "FROM Quarto q "
		         + "WHERE q.numero = :numero "
		         + " or q.tipo = :tipo "
		         + " or q.servico = :servico ")

		         .setParameter("numero", criterio.trim().toLowerCase())
		         .setParameter("tipo", criterio.trim().toLowerCase())
		         .setParameter("servico", criterio.trim().toLowerCase())
		         .getResultList();
	}

}
