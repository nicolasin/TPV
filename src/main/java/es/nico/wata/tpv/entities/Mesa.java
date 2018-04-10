package es.nico.wata.tpv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name="Mesas")

public class Mesa {
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@Column(name="nombre")
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
