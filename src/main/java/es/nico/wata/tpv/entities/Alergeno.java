package es.nico.wata.tpv.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.*;
@Entity
@Table(name="Alergenos")
public class Alergeno implements Serializable{

	private static final long serialVersionUID = 8170724969518059162L;
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@Column(name="nombre")
	String nombre;
	@ManyToMany(mappedBy = "alergenos")
	private Set<Componente> componentes = new HashSet<>();
	public Alergeno() {
		
	}
	public Alergeno(String nombre) {
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
	
	public Set<Componente> getComponentes() {
		return componentes;
	}
	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}
	@Override
	public String toString() {
		return "Alergeno [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
