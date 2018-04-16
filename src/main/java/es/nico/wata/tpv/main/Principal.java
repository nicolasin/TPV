package es.nico.wata.tpv.main;

import java.time.LocalDate;
import java.util.*;

import es.nico.wata.tpv.controladores.*;
import es.nico.wata.tpv.entities.Alergeno;
import es.nico.wata.tpv.entities.Categoria;
import es.nico.wata.tpv.entities.Componente;
import es.nico.wata.tpv.entities.Descuento;
import es.nico.wata.tpv.entities.Pedido;
import es.nico.wata.tpv.entities.Producto;
import es.nico.wata.tpv.interfaces.ControlInterface;
import net.bytebuddy.pool.TypePool.CacheProvider;

public class Principal {
	private static final String persistence = "mysql";

	public static void main(String[] args) throws Exception {
	ControlPedido cp = new ControlPedido(persistence);
	ControlProducto cprod = new ControlProducto(persistence);
	ControlDescuento cd = new ControlDescuento(persistence);
	Descuento d = cd.getOne(3L);
	
		
	}
}
