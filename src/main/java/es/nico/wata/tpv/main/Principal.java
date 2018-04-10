package es.nico.wata.tpv.main;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.entities.Descuento;
import es.nico.wata.tpv.entities.FormaDePago;
import es.nico.wata.tpv.entities.Mesa;
import es.nico.wata.tpv.entities.Pedido;


public class Principal {
	private static EntityManagerFactory  emf;
	public static void main(String [] args) {
		emf = Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		Pedido pedido1 = new Pedido();
		Mesa mesa1 = manager.find(Mesa.class,1L);
		Descuento descAdministracion = manager.find(Descuento.class, 3L);
		FormaDePago visa = manager.find(FormaDePago.class, 1L);
		/*
		pedido1.setFecha(LocalDate.now());
		pedido1.setEstaPagado(false);
		pedido1.setTotal(13.5);
		pedido1.setDescuento(descAdministracion);
		pedido1.setFormadePago(visa);
		pedido1.setMesa(mesa1);
		pedido1.setDescripccion("");
		manager.persist(pedido1);
		
		List<FormaDePago> mesas = manager.createQuery(" from FormaDePago where id > 1").getResultList();
		List<Descuento>descuentos = manager.createQuery("from Descuento where id > 0").getResultList();
		descuentos.forEach(System.out::println);
		mesas.forEach(System.out::println)*/;
		
		List<Pedido> pedidosMesa1 = manager.createQuery("from Pedido where mesa = "+mesa1.getId()).getResultList();

		pedidosMesa1.forEach(System.out::println);
		manager.getTransaction().commit();
		manager.close();
		
		

		
	}
}
