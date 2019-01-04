package com.lpoo2.trabalhoJPA.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManagerFactory emf;

	public static EntityManager getEM() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("trabalhojpa");
		}
		return emf.createEntityManager();
	}

	public static void fechaEmf() {
		emf.close();
	}

}