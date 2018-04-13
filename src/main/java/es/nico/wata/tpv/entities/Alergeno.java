package es.nico.wata.tpv.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

import java.util.*;
@Entity
@Table(name="Alergenos")
public class Alergeno implements Serializable{

	private static final long serialVersionUID = 8170724969518059162L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	public void addComponente(Componente c) {
		if(!componentes.contains(c)) {
			componentes.add(c);
			c.addAlergeno(this);
		}
	}
	public void removeComponente(Componente c) {
		if(componentes.contains(c)) {
			componentes.remove(c);
			c.removeAlergeno(this);
		}
	}
	@Override
	public String toString() {
		return "Alergeno [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
