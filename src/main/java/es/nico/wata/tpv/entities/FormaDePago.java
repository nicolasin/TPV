package es.nico.wata.tpv.entities;

public class FormaDePago {
	Long id;
	String nombre;
	public FormaDePago() {
		
	}
	public FormaDePago(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "FormasDePago [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
