package es.nico.wata.tpv.entities;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="Componentes")
public class Componente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	@Column(name="nombre")
	String nombre;
	@ManyToMany()
	@JoinTable(name="ComponentesAlergenos", joinColumns= {@JoinColumn(name="idComponentes")}, inverseJoinColumns= {@JoinColumn(name="idAlergenos")})
	private Set<Alergeno> alergenos = new HashSet<>();
	@Column(name="descripcion")
	String descripcion;
	@Column(name="stock")
	Long stock;
	@OneToMany(mappedBy="componente")
	Set<ComponenteProducto> productos = new HashSet<>();
	public Componente() {
		
	}
	public Componente(String nombre, String descripcion, Long stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.stock = stock;
		
	}
	
	public Set<ComponenteProducto> getProductos() {
		return productos;
	}
	public void setProductos(Set<ComponenteProducto> productos) {
		this.productos = productos;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public Set<Alergeno> getAlergenos() {
		return alergenos;
	}
	public void setAlergenos(Set<Alergeno> alergenos) {
		this.alergenos = alergenos;
	}
	public void addAlergeno(Alergeno a) {	
			alergenos.add(a);
	}
	public void removeAlergeno(Alergeno a) {
		alergenos.remove(a);
	}
	@Override
	public String toString() {
		return "Componente [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock
				+ "]";
	}
	

}
