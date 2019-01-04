package com.lpoo2.trabalhoJPA.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lpoo2.trabalhoJPA.pojo.Diaria;
import com.lpoo2.trabalhoJPA.util.EntityManagerUtil;

public class DiariaDAO {
	private EntityManager em;
	
    public void salva(Diaria diaria) {
    	em = EntityManagerUtil.getEM();
    	em.getTransaction().begin();
		em.persist(diaria);
		em.getTransaction().commit(); 
		em.close();
    }
    public void atualiza(Diaria diaria) {
    	em = EntityManagerUtil.getEM();
		em.getTransaction().begin();
		em.merge(diaria);
		em.getTransaction().commit(); 
		em.close();
	
    }

    public void remove(Long id) {
    	em = EntityManagerUtil.getEM();
    	em.getTransaction().begin();
    	Diaria diaria= em.find(Diaria.class, id);
    	if (diaria != null) {
    		em.persist(diaria);
    		em.remove(diaria);
    		em.getTransaction().commit();    		
    		em.close();
    	}
    }

    public Diaria busca(Long id) {
    	em = EntityManagerUtil.getEM();
    	Diaria diaria = em.find(Diaria.class, id);
    	return diaria;
    }

    public List<Diaria> buscaTodos() {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Diaria> query = em.createQuery("SELECT dia FROM Diaria dia", Diaria.class);
		List<Diaria> usuarios = query.getResultList();
		return usuarios;
    }

    public List<Diaria> buscaPorData(Date data) {
    	em = EntityManagerUtil.getEM();
    	TypedQuery<Diaria> query = em.createQuery("SELECT dia FROM Diaria dia where dia.data = :data", Diaria.class);
		query.setParameter("data", data);
		List <Diaria> diarias = query.getResultList();
		return diarias;
    }
 
}
