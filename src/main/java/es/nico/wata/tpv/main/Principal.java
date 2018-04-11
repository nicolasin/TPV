package es.nico.wata.tpv.main;

import java.util.Scanner;

import es.nico.wata.tpv.controladores.ControlAlergeno;

public class Principal {

	public static void main(String [] args)throws Exception {
		ControlAlergeno.listAlergenos().forEach(System.out::println);
		System.out.println("Deme alergeno a borrar");
		Scanner s = new Scanner(System.in);
		Long id = s.nextLong();
		ControlAlergeno.remove(id);
		
	}
}
