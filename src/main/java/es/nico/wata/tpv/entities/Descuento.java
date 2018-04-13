package es.nico.wata.tpv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Descuentos")
public class Descuento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	@Column(name="nombre")
	String nombre;
	@Column(name="porcentaje")
	Long porcentaje;
	@Column(name="descripcion")
	String descripcion;
	
	public Descuento() {
		
	}
	public Descuento(String nombre, Long porcentaje, String descripccion) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.descripcion = descripccion;
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
		return descripcion;
	}
	public void setDescripccion(String descripccion) {
		this.descripcion = descripccion;
	}
	@Override
	public String toString() {
		return "Descuento [id=" + id + ", nombre=" + nombre + ", porcentaje=" + porcentaje + ", descripccion="
				+ descripcion + "]";
	}
	
}
