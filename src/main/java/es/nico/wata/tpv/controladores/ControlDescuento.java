package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import es.nico.wata.tpv.entities.Descuento;
import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.exceptions.EntityExist;
import es.nico.wata.tpv.exceptions.IncorrectEntity;
import es.nico.wata.tpv.interfaces.ControlInterface;

public class ControlDescuento implements ControlInterface<Descuento, Long>{
	private static EntityManagerFactory  emf;
	private final String GETALL = "From Descuento";
	public ControlDescuento(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}
	@Override
	public void insert(Descuento t) throws ControlException {
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
	public Descuento getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Descuento t = null;
		try {
			t = manager.find(Descuento.class, i);
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
	public List<Descuento> getAll() throws ControlException {
		List<Descuento> listElements = new ArrayList<Descuento>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Descuento>) manager.createQuery(GETALL).getResultList();
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
			Descuento t = manager.find(Descuento.class,i);
			manager.remove(t);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}
		
	}

	@Override
	public void modify(Descuento t) throws ControlException {
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
