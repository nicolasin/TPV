package es.nico.wata.tpv.entities;

public class Mesa {
	Long id;
	String nombre;
	public Mesa() {}
	public Mesa(String nombre) {
		
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
		return "Mesa [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
