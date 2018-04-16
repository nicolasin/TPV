package es.nico.wata.tpv.controladores;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.entities.Producto;
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

	public List<Alergeno> listAlergenosByComponente(Componente c) {
		List<Alergeno> alergenos = new ArrayList<Alergeno>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		c = manager.merge(c);
		alergenos.addAll(c.getAlergenos());
		manager.getTransaction().commit();
		manager.close();
		return alergenos;
	}

	@SuppressWarnings("unchecked")
	public List<Alergeno> listAlergenoByProducto(Producto p) {
		List<Alergeno> alergenosProductos = new ArrayList<Alergeno>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		String sql = "Select * from Alergenos  where id IN "
				+ "(Select idALergenos from ComponentesAlergenos where idComponentes IN "
				+ "(Select idComponente from ComponentesProductos where idProducto = " + p.getId() + "));";
		alergenosProductos = (List<Alergeno>) manager.createNativeQuery(sql, Alergeno.class).getResultList();
		manager.getTransaction().commit();
		manager.close();
		return alergenosProductos;
	}

	
	public List<Alergeno> getByName(String name) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		List<Alergeno> t = new ArrayList<Alergeno>();
		try {
			t = manager.createQuery("from Alergeno a where a.nombre LIKE '%" + name + "%' ", Alergeno.class)
					.getResultList();
			manager.getTransaction().commit();

		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}
		return t;

	}
}
