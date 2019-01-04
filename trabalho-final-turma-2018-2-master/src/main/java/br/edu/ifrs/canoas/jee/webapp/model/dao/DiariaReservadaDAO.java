package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaReservada;

public class DiariaReservadaDAO extends BaseDAO<DiariaReservada, Long> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<DiariaReservada> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM DiariaReservada u "
		         + "WHERE u.data = :data "
		         + " or u.qtdDias = :qtdDias "
		         + " or u.reserva_id = :reserva_id "
				)				
				 .setParameter("data", criterio.trim().toLowerCase())
		         .setParameter("qtdDias", criterio.trim().toLowerCase())
		         .setParameter("reserva_id", criterio.trim().toLowerCase())
		         .getResultList();
	}
}
