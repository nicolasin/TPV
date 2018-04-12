package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.exceptions.EntityExist;
import es.nico.wata.tpv.exceptions.IncorrectEntity;
import es.nico.wata.tpv.interfaces.ControlInterface;
import es.nico.wata.tpv.entities.*;
public class ControlMesa implements ControlInterface<Mesa, Long> {
	private static EntityManagerFactory  emf;
	private final String GETALL = "From Mesa";

	public ControlMesa(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}
	@Override
	public void insert(Mesa t) throws ControlException {
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
	public Mesa getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Mesa t = null;
		try {
			t = manager.find(Mesa.class, i);
			manager.getTransaction().commit();

		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mesa> getAll() throws ControlException {
		List<Mesa> listElements = new ArrayList<Mesa>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Mesa>) manager.createQuery(GETALL).getResultList();
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
			Mesa t = manager.find(Mesa.class,i);
			manager.remove(t);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}
		
	}

	@Override
	public void modify(Mesa t) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			manager.merge(t);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}
		
	}
}
