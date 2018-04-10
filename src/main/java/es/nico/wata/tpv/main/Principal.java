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
		Producto producto = manager.find(Producto.class, 1L);
		System.out.println(producto);
		producto.getPedidos().forEach(System.out::println);;
		
		
		manager.getTransaction().commit();
		manager.close();
		

	}
}
