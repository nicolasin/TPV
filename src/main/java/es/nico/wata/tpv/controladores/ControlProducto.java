package es.nico.wata.tpv.controladores;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControlProducto {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
	
}
