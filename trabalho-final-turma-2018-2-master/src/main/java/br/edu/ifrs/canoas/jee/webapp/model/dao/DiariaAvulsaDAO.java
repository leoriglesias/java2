package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;

import br.edu.ifrs.canoas.jee.webapp.model.entity.DiariaAvulsa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Pessoa;
import br.edu.ifrs.canoas.jee.webapp.model.entity.Quarto;

public class DiariaAvulsaDAO extends BaseDAO<DiariaAvulsa, Long> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<DiariaAvulsa> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT da "
		         + "FROM DiariaAvulsa da "
		         + "WHERE da.qtdDias= :qtdDias "
		         + " or da.data = :data "
		         + " or da.pessoa.cpf = :cpf "
		         + " or da.pessoa.cnpj = :cnpj "
		         + " or da.quarto.numero = :numero ")

		         .setParameter("qtdDias", criterio.trim().toLowerCase())
		         .setParameter("data", criterio.trim().toLowerCase())
		         .setParameter("cpf", criterio.trim().toLowerCase())
		         .setParameter("cnpj", criterio.trim().toLowerCase())
		         .setParameter("numero", criterio.trim().toLowerCase())
		         .getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Quarto> buscaQuartos(Long id){
		return em.createQuery(
		         "SELECT q "
		         + "FROM Quarto q "
		         + "WHERE id not in( "
		         + " SELECT d.quarto from Diaria d "
				 + " WHERE d.data = curdate()) "
		 		 + " or q.id = :id "
				 ).setParameter("id", id)
				.getResultList();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Quarto> buscaQuartos(){
//		return em.createQuery(
//		         "SELECT q "
//		         + "FROM Quarto q "
//		         + "WHERE id not in( "
//		         + " SELECT d.quarto from Diaria d "
//				 + " WHERE d.data = curdate()) ").getResultList();
//	}

	@SuppressWarnings("unchecked")
	public List<DiariaAvulsa> buscaDiariasAvulsas(){
		return em.createQuery(
		         "SELECT da "
		         + "FROM DiariaAvulsa da "
		         + "order by da.quarto.numero ").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> buscaPessoaPorID(Long id) {
		return em.createQuery("SELECT u " + "FROM DiariaAvulsa u " + "WHERE u.pessoa.id = :id")
				.setParameter("id", id).getResultList();
	}
}