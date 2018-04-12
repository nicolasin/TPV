package es.nico.wata.tpv.controladores;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import es.nico.wata.tpv.exceptions.*;
import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.interfaces.ControlInterface;

public class Controlador<T, I> implements ControlInterface<T, I> {
	private EntityManagerFactory emf;

	Controlador(String persistencia) {
		emf = Persistence.createEntityManagerFactory(persistencia);
	}

	@Override
	public void insert(T t) throws ControlException {
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

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public T getOne(I i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		T t = null;
		try {
			t =  (T) manager.find(t.getClass(), i);
			manager.getTransaction().commit();
			
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}
		return  t;
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public List<T> getAll() throws ControlException {
		List<T> listElements = null;
		T t = null;
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<T>) manager.createQuery("from "+t.getClass().getName()+" ");
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e){
			throw new  IncorrectEntity("Type of Entity is Incorrect");
		}finally { 
			manager.close();
		}
		
		return listElements;
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public void remove(I i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		T t = null;
		try {
			t = (T) manager.find(t.getClass(),i);
			manager.remove(t);
			manager.getTransaction().commit();
		}catch(IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		}finally {
			manager.close();
		}
	}

	@Override
	public void modify(T t) throws ControlException {
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
