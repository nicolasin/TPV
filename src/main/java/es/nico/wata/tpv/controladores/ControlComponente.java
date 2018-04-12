package es.nico.wata.tpv.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.exceptions.IDNotFound;

public class ControlComponente {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");

	public static void insert(String nombre, String descripccion, long Stock) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Componente componente = new Componente(nombre, descripccion, Stock);
		manager.persist(componente);
		manager.getTransaction().commit();
		manager.close();
	}

	public static void delete(long id) throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		Componente componente = null;
		manager.getTransaction().begin();
		try {
			componente = manager.find(Componente.class, id);
			if (componente != null) {
				manager.remove(componente);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Componente ID not found");
		} finally {
			manager.close();
		}
	}

	public static List<Componente> getAll() {
		EntityManager manager = emf.createEntityManager();
		List<Componente> componentes = (List<Componente>) manager.createQuery("from Componente").getResultList();
		manager.close();
		return componentes;
	}

	public static Componente getOne(long id) throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		Componente componente = null;

		try {
			manager.getTransaction().begin();
			componente = manager.find(Componente.class, id);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Componente Not Found");
		} finally {
			manager.close();
		}
		return componente;
	}
	public static void modifyStock(long id, long stock)throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		Componente componente = null;
		try {
			manager.getTransaction().begin();
			componente = manager.find(Componente.class, id);
			componente.setStock(stock);
			manager.merge(componente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Componente Not Found");
		} finally {
			manager.close();
		}
	}
	public static void modifyStock(long id, String nombre)throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		Componente componente = null;
		try {
			manager.getTransaction().begin();
			componente = manager.find(Componente.class, id);
			componente.setNombre(nombre);
			manager.merge(componente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Componente Not Found");
		} finally {
			manager.close();
		}
	}
	public static void modifyDescripcion(long id, String descripcion)throws IDNotFound {
		EntityManager manager = emf.createEntityManager();
		Componente componente = null;
		try {
			manager.getTransaction().begin();
			componente = manager.find(Componente.class, id);
			componente.setDescripcion(descripcion);
			manager.merge(componente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new IDNotFound("Componente Not Found");
		} finally {
			manager.close();
		}
	}
	public static void modify(Componente c) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(c);
		manager.getTransaction().commit();
		manager.close();
	}
	
}
