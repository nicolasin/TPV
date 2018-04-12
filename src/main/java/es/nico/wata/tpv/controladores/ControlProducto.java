package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Producto;
import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.exceptions.EntityExist;
import es.nico.wata.tpv.exceptions.IncorrectEntity;
import es.nico.wata.tpv.interfaces.ControlInterface;

public class ControlProducto implements ControlInterface<Producto, Long> {
	private static EntityManagerFactory emf;
	private final String GETALL = "From Producto";

	public ControlProducto(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}

	@Override
	public void insert(Producto t) throws ControlException {
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
	public Producto getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Producto t = null;
		try {
			t = manager.find(Producto.class, i);
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
	public List<Producto> getAll() throws ControlException {
		List<Producto> listElements = new ArrayList<Producto>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Producto>) manager.createQuery(GETALL).getResultList();
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
			Producto t = manager.find(Producto.class, i);
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}

	}

	@Override
	public void modify(Producto t) throws ControlException {
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
}
