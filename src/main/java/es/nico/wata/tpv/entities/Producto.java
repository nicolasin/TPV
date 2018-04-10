package es.nico.wata.tpv.entities;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="Productos")
public class Producto {
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@Column(name="nombre")
	String nombre;
	@Column(name="precio")
	double precio;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CategoriasProductos",joinColumns= {@JoinColumn(name="idProducto")}, inverseJoinColumns= {@JoinColumn(name="idCategoria")})
	Set<Categoria> categorias = new HashSet<>();
	@Column(name="descripcion")
	String descripcion;
	
	public Producto() {}
	public Producto(String nombre, double precio) {
		
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Set<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}
	public String getDescripccon() {
		return descripcion;
	}
	public void setDescripccon(String descripccon) {
		this.descripcion = descripccon;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categorias=" + categorias
				+ ", descripccon=" + descripcion + "]";
	}
	
	
}
