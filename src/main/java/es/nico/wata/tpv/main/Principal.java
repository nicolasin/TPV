package es.nico.wata.tpv.main;
import java.util.*;

import es.nico.wata.tpv.controladores.*;
import es.nico.wata.tpv.interfaces.ControlInterface;
public class Principal {

	@SuppressWarnings("unchecked")
	public static void main(String [] args)throws Exception {
		@SuppressWarnings("rawtypes")
		List<ControlInterface> Controladores  =new ArrayList<ControlInterface>();
		String persistence = "mysql";
		Controladores.add(new ControlAlergeno(persistence));
		Controladores.add(new ControlCategoria(persistence));
		Controladores.add(new ControlComponente(persistence));
		Controladores.add(new ControlDescuento(persistence));
		Controladores.add(new ControlFormaDePago(persistence));
		Controladores.add(new ControlMesa(persistence));
		Controladores.add(new ControlPedido(persistence));
		Controladores.add(new ControlProducto(persistence));
		
		for(@SuppressWarnings("rawtypes") ControlInterface x: Controladores) {
			x.getAll().forEach(System.out::println);
		}
		
		
	}
}
