package es.nico.wata.tpv.controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.exceptions.IDNotFound;

import java.util.*;
public class ControlAlergeno {
	private static EntityManagerFactory  emf = Persistence.createEntityManagerFactory("mysql");;
	
	
	public static void add(String name) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(new Alergeno(name));
		manager.getTransaction().commit();
		manager.close();
	}
	public static void remove(Long id) throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
		manager.remove(manager.find(Alergeno.class,id));
		}catch(Exception e) {
			throw new IDNotFound("Alergeno id not found");
		}
		manager.getTransaction().commit();
		manager.close();
	}
	@SuppressWarnings("unchecked")
	public static List<Alergeno> listAlergenos(){
		EntityManager manager = emf.createEntityManager();
		List<Alergeno> alergenos = (List<Alergeno>)manager.createQuery("from Alergeno").getResultList();
		List<String> paraRetornar = new ArrayList<String>();
		for(Alergeno x: alergenos) {
			paraRetornar.add(x.toString());
		}
		manager.close();
		return alergenos;
	}
	
}
