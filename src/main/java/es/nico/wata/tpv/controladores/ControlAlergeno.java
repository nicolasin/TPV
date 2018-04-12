package es.nico.wata.tpv.controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.exceptions.IDNotFound;

import java.util.*;

public class ControlAlergeno {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");

	public static void add(String name) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(new Alergeno(name));
		manager.getTransaction().commit();
		manager.close();
	}

	public static void remove(Long id) throws IDNotFound {
		Alergeno alergeno = null;
		EntityManager manager = emf.createEntityManager();

		try {
			manager.getTransaction().begin();
			alergeno = manager.find(Alergeno.class, id);
			if (alergeno != null) {
				manager.remove(alergeno);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Alergeno id not found");
		} finally {
			manager.close();
		}

	}

	@SuppressWarnings("unchecked")
	public static List<Alergeno> listAlergenos() {
		EntityManager manager = emf.createEntityManager();
		List<Alergeno> alergenos = (List<Alergeno>) manager.createQuery("from Alergeno").getResultList();
		manager.close();
		return alergenos;
	}

}
