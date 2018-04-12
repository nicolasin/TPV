package es.nico.wata.tpv.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="Categorias")
public class Categoria implements Serializable{
	private static final long serialVersionUID = 2254120630994200045L;
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@Column(name="nombre")
	String nombre;
	@ManyToMany(mappedBy="categorias")
	Set<Producto> productos = new HashSet<>();
	public Categoria() {
		
	}
	
	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	public Categoria(String nombre) {
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
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
