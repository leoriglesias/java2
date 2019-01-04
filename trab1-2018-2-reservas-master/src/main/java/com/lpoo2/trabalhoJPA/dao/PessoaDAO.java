package com.lpoo2.trabalhoJPA.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lpoo2.trabalhoJPA.pojo.Pessoa;
import com.lpoo2.trabalhoJPA.pojo.PessoaFisica;
import com.lpoo2.trabalhoJPA.pojo.PessoaJuridica;
import com.lpoo2.trabalhoJPA.util.EntityManagerUtil;

public class PessoaDAO {
	private EntityManager em;
	
    public void salva(Pessoa pessoa) {
    	em = EntityManagerUtil.getEM();
    	em.getTransaction().begin();
		em.persist(pessoa);
		em.getTransaction().commit(); 
		em.close();
    }
    public void atualiza(Pessoa pessoa) {
    	em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(pessoa);
		em.getTransaction().commit(); 
		em.close();
	
    }

    public void remove(Long id) {
    	em = EntityManagerUtil.getEM();
    	em.getTransaction().begin();
    	Pessoa pessoa = em.find(Pessoa.class, id);
    	if (pessoa != null) {
    		em.persist(pessoa);
    		em.remove(pessoa);
    		em.getTransaction().commit();    		
    		em.close();
    	}
    }

    public PessoaFisica busca(Long id) {
    	em = EntityManagerUtil.getEM();
    	PessoaFisica pessoa = em.find(PessoaFisica.class, id);
    	return pessoa;
    }
    
    public List<Pessoa> buscaTodos() {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Pessoa> query = em.createQuery("SELECT pes FROM Pessoa pes", Pessoa.class);
		List<Pessoa> usuarios = query.getResultList();
		return usuarios;
    }

    public List<Pessoa> buscaPorEmail(String email) {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Pessoa> query = em.createQuery("SELECT pes FROM Pessoa pes where pes.email = :email", Pessoa.class);
		query.setParameter("email", email);
		List <Pessoa> usuarios = query.getResultList();
		return usuarios;
    }
    
    public List<Pessoa> buscaPorTelefone(String telefone) {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Pessoa> query = em.createQuery("SELECT pes FROM Pessoa pes where pes.telefone = :telefone", Pessoa.class);
		query.setParameter("telefone", telefone);
		List <Pessoa> usuarios = query.getResultList();
		return usuarios;
    }
}