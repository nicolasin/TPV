package es.nico.wata.tpv.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Componente;


public class Principal {
	private static EntityManagerFactory  emf;
	public static void main(String [] args) {
		emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		Alergeno a1 = manager.find(Alergeno.class,4L);
		
		Componente c1 = manager.find(Componente.class, 1L);
		c1.addAlergeno(a1);
		
		manager.getTransaction().commit();
		manager.close();
		System.out.println(a1);
		
	}
}
