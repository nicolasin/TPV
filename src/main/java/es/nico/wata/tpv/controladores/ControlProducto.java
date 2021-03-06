package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Categoria;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.entities.ComponenteProducto;
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
	@SuppressWarnings("unchecked")
	public List<Producto> productsWithAlergen(Alergeno a){
		List<Producto> productosAlergeno = new ArrayList<Producto>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		a = manager.merge(a);
		String sql =  "Select * from Productos where id IN (Select idProducto from ComponentesProductos where "+
		"idComponente IN(Select idComponentes from ComponentesAlergenos where idAlergenos = "+a.getId()+") group by idProducto )";
		productosAlergeno = (List<Producto>)manager.createNativeQuery(sql, Producto.class).getResultList();
		return productosAlergeno;
	}
	public List<Producto> listProductsByComponent(Componente c) {
		List<Producto> productos = new ArrayList<Producto>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		c = manager.merge(c);
		c.getProductos().forEach(x->productos.add(x.getProducto()));
		manager.getTransaction().commit();
		manager.close();
		return productos;
	}
	@SuppressWarnings("unchecked")
	public List<Producto> listProductsByCategorie(Categoria c){
		List<Producto> productosCategoria = new ArrayList<Producto>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		c = manager.merge(c);
		String sqlQuery = "Select * from productos where id IN (Select idProducto from CategoriasProductos where idCategoria = "
				+c.getId()+" ) ;";
		productosCategoria = (List<Producto>)manager.createNativeQuery(sqlQuery, Producto.class).getResultList();
		manager.close();
		return productosCategoria;
	}
	public void addCategoriaToProducto(Categoria c, Producto p) {
		EntityManager manager  = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		c = manager.merge(c);
		p.addCategoria(c);
		manager.getTransaction().commit();
		manager.close();
	}
	public void removeCategoriaToProducto(Categoria c, Producto p) {
		EntityManager manager  = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		c = manager.merge(c);
		p.removeCategoria(c);
		manager.getTransaction().commit();
		manager.close();
	}
	public void addComponenteToProducto(Componente c, Long cantidad, Producto p) {
		EntityManager manager  = emf.createEntityManager();
		manager.getTransaction().begin();
		
		p = manager.merge(p);
		c = manager.merge(c);
		ComponenteProducto cp = new ComponenteProducto(c, p, cantidad);
		manager.persist(cp);
		p.getComponentes().add(cp);
		c.getProductos().add(cp);
		
		manager.getTransaction().commit();
		manager.close();
	}
	public void removeComponenteToProducto(Componente c, Producto p) {
		EntityManager manager  = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		c = manager.merge(c);
		ComponenteProducto comProd=null;
		for(ComponenteProducto cp: p.getComponentes()) {
			if(cp.getComponente().getId()==c.getId()) {
				comProd = cp;
			}
		}
		if(comProd!=null) {
			comProd = manager.merge(comProd);
			manager.remove(comProd);
		}
		manager.getTransaction().commit();
		manager.close();
		
	}

	public List<Producto> getByName(String name) throws ControlException {
		List<Producto> productos = new ArrayList<Producto>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		productos = manager.createQuery("from Producto p where p.nombre ILIKE '%"+name+"%' ", Producto.class).getResultList();
		manager.close();
		return productos;
	}
	public List<Componente> getComponentes(Producto p){
		List<Componente> componentes = new ArrayList<Componente>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		componentes.addAll(p.getComponentes().stream().map(x->x.getComponente()).collect(Collectors.toList()));
		manager.close();
		return componentes;
	}

	
}
