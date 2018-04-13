package es.nico.wata.tpv.main;
import java.util.*;

import es.nico.wata.tpv.controladores.*;
import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.entities.Pedido;
import es.nico.wata.tpv.entities.Producto;
import es.nico.wata.tpv.interfaces.ControlInterface;
public class Principal {
	private static final String persistence = "mysql";
	@SuppressWarnings("unchecked")
	public static void main(String [] args)throws Exception {
		
		ControlProducto cp = new ControlProducto(persistence);
		ControlAlergeno ca = new ControlAlergeno(persistence);
		Alergeno a = ca.getOne(2L);
		cp.productosConAlergeno(a);
		

		
		
		
	}
}
