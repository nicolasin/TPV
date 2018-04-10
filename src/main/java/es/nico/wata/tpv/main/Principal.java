package es.nico.wata.tpv.main;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Pedido;
import es.nico.wata.tpv.entities.PedidoProducto;
import es.nico.wata.tpv.entities.Producto;


public class Principal {
	private static EntityManagerFactory  emf;
	public static void main(String [] args) {
		emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		Pedido unPedido = manager.find(Pedido.class, 7L);
		Producto prod1 = manager.find(Producto.class, 1L);
		Producto prod2 = manager.find(Producto.class, 3L);
		unPedido.getProductospedido().add(new PedidoProducto(unPedido, prod1, 2));
		unPedido.getProductospedido().add(new PedidoProducto(unPedido, prod2, 1));
		
		manager.persist(unPedido);
		manager.getTransaction().commit();
		manager.close();
		

	}
}
