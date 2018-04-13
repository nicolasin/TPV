package es.nico.wata.tpv.controladores;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.exceptions.*;
import es.nico.wata.tpv.interfaces.ControlInterface;

import java.util.*;

public class ControlAlergeno implements ControlInterface<Alergeno, Long> {
	private static EntityManagerFactory emf;
	private final String GETALL = "From Alergeno";
	public ControlAlergeno(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}
	@Override
	public void insert(Alergeno t) throws ControlException {

		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			manager.persist(t);
			manager.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EntityExist("This Entity exist yet");
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("This class is not Entity");
		} finally {
			manager.close();
		}

	}

	@Override
	public Alergeno getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Alergeno a = null;
		try {
			a = manager.find(Alergeno.class, i);
			manager.getTransaction().commit();

		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alergeno> getAll() throws ControlException {
		List<Alergeno> listElements = new ArrayList<Alergeno>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Alergeno>) manager.createQuery(GETALL).getResultList();
			manager.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Type of Entity is Incorrect");
		} finally {
			manager.close();
		}

		return listElements;
	}

	@Override
	public void remove(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			Alergeno a = manager.find(Alergeno.class, i);
			manager.remove(a);
			manager.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}
	}

	@Override
	public void modify(Alergeno t) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			manager.merge(t);
			manager.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}
	}
	
	public List<Componente> listComponenteWithAlergeno(Alergeno t)throws ControlException{
		List<Componente> componentes = new ArrayList<Componente>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		t = manager.merge(t);
		componentes.addAll(t.getComponentes());
		manager.getTransaction().commit();
		manager.close();
		return componentes;
	}
}
