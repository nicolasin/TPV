package es.nico.wata.tpv.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.interfaces.ControlInterface;
import es.nico.wata.tpv.entities.*;
import es.nico.wata.tpv.exceptions.ControlException;
import es.nico.wata.tpv.exceptions.EntityExist;
import es.nico.wata.tpv.exceptions.IncorrectEntity;

public class ControlPedido implements ControlInterface<Pedido, Long> {
	private static EntityManagerFactory emf;
	private final String GETALL = "From Pedido";

	public ControlPedido(String persistence) {
		emf = Persistence.createEntityManagerFactory(persistence);
	}

	@Override
	public void insert(Pedido t) throws ControlException {
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
	public Pedido getOne(Long i) throws ControlException {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Pedido t = null;
		try {
			t = manager.find(Pedido.class, i);
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
	public List<Pedido> getAll() throws ControlException {
		List<Pedido> listElements = new ArrayList<Pedido>();
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		try {
			listElements = (List<Pedido>) manager.createQuery(GETALL).getResultList();
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
			Pedido t = manager.find(Pedido.class, i);
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (IllegalArgumentException e) {
			throw new IncorrectEntity("Incorrect Entity type");
		} finally {
			manager.close();
		}

	}

	@Override
	public void modify(Pedido t) throws ControlException {
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

	public Set<PedidoProducto> getProductosPedido(Pedido p) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		p = manager.merge(p);
		String sql = "from PedidoProducto p where p.pedido.id = "+p.getId()+"";
		Set<PedidoProducto>productos = (Set<PedidoProducto>)manager.createQuery(sql,PedidoProducto.class).getResultList().stream().collect(Collectors.toSet());
		manager.getTransaction().commit();
		manager.close();
		return productos;
	}

	public List<Pedido> listPedidosByFormaDePago(FormaDePago fp) {
		List<Pedido> pedidos = null;
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		String querySql = "from Lista l where idFormaPago = " + fp.getId() + "";
		pedidos = (List<Pedido>) manager.createQuery(querySql, Pedido.class).getResultList();
		manager.getTransaction().commit();
		manager.close();
		return pedidos;
	}

	public List<Pedido> listPedidosByMesa(Mesa m) {
		List<Pedido> pedidos = null;
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		String querySql = "from Lista l where idMesa = " + m.getId() + "";
		pedidos = (List<Pedido>) manager.createQuery(querySql, Pedido.class).getResultList();
		manager.getTransaction().commit();
		manager.close();
		return pedidos;

	}
	public void addProductoToPedido(Producto p, Pedido pe, double cantidad) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
			p = manager.merge(p);
			pe = manager.merge(pe);
			
			PedidoProducto pp = new PedidoProducto(pe, p, cantidad);
			pe.setTotal(pe.getTotal()+(p.getPrecio()*cantidad));
			manager.persist(pp);
			pe.addProducto(p, cantidad);
		manager.getTransaction().commit();
		manager.close();
	}
}
