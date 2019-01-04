package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifrs.canoas.jee.webapp.model.entity.Pessoa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Reserva;

public class ReservaDAO extends BaseDAO<Reserva, Long> {

	private static final long serialVersionUID = 1L;
	@Inject
    protected EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Reserva> buscaPorData(Date data) {
		return em.createQuery("SELECT r " + "FROM Reserva r " + "WHERE r.data = :data").setParameter("data", data)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscaPorCriterio(String criterio) {
		return em.createQuery(
		         "SELECT u "
		         + "FROM Reserva u "
		         + "WHERE u.data = :data "
		         + " or u.valor = :valor "
		         + " or u.pessoa = :pessoa ")

		         .setParameter("data", criterio.trim().toLowerCase())
		         .setParameter("valor", criterio.trim().toLowerCase())
		         .setParameter("pessoa", criterio.trim().toLowerCase())
		         .getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reserva> buscaPessoaPorID(Long id) {
		 return em.createQuery("SELECT u " + "FROM Reserva u " + "WHERE u.pessoa.id = :id")
				.setParameter("id", id).getResultList();

	}

}
