package es.nico.wata.tpv.entities;

public class Descuento {
	Long id;
	String nombre;
	Long porcentaje;
	String descripccion;
	public Descuento() {
		
	}
	public Descuento(String nombre, Long porcentaje, String descripccion) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.descripccion = descripccion;
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
	public Long getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Long porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getDescripccion() {
		return descripccion;
	}
	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}
	@Override
	public String toString() {
		return "Descuento [id=" + id + ", nombre=" + nombre + ", porcentaje=" + porcentaje + ", descripccion="
				+ descripccion + "]";
	}
	
}
