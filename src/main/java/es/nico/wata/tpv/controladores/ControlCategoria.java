package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.*;
import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.exceptions.EntityExist;
import es.nico.wata.tpv.exceptions.IncorrectEntity;
import es.nico.wata.tpv.interfaces.ControlInterface;

public class ControlCategoria implements ControlInterface<Categoria, Long> {
	private static EntityManagerFactory emf;
	private final String GETALL = "From Categoria";

	public ControlCategoria(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}

	@Override
	public void insert(Categoria t) throws ControlException {
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
	public Categoria getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Categoria t = null;
		try {
			t = manager.find(Categoria.class, i);
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
	public List<Categoria> getAll() throws ControlException {
		List<Categoria> listElements = new ArrayList<Categoria>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Categoria>) manager.createQuery(GETALL).getResultList();
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
			Categoria t = manager.find(Categoria.class,i);
			manager.remove(t);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}

	}

	@Override
	public void modify(Categoria t) throws ControlException {
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
