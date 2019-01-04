package br.edu.ifrs.canoas.jee.webapp.model.dao;

import java.util.List;
import br.edu.ifrs.canoas.jee.webapp.model.entity.PessoaFisica;
public class PessoaFisicaDAO extends BaseDAO<PessoaFisica, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 //CONSULTAS ESPECIFICAS
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> g(String cpf) {
		return em.createQuery("SELECT u " + "FROM PessoaFisica u " + "WHERE u.cpf = :cpf")
				.setParameter("cpf", cpf).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> buscaPorRg(String rg) {
		return em.createQuery("SELECT u " + "FROM PessoaFisica u " + "WHERE u.rg = :rg")
				.setParameter("rg", rg).getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM PessoaFisica u "
		         + "WHERE u.cpf = :cpf "
		         + " or u.rg = :rg "
		         + " or u.email = :email "
		         + " or u.nome = :nome "
		         + " or u.telefone =  :telefone ")
				
		         .setParameter("email", criterio.trim().toLowerCase())
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .setParameter("cpf", criterio.trim().toLowerCase())
		         .setParameter("rg", criterio.trim().toLowerCase())
		         .setParameter("telefone", criterio.trim().toLowerCase())
		         .getResultList();
	}
	

	
}
