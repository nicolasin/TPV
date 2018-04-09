package es.nico.wata.tpv.entities;
import java.util.*;

public class Producto {
	Long id;
	String nombre;
	double precio;
	Set<Categoria> categorias = new HashSet<Categoria>();
	String descripccon;
	
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
		return descripccon;
	}
	public void setDescripccon(String descripccon) {
		this.descripccon = descripccon;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categorias=" + categorias
				+ ", descripccon=" + descripccon + "]";
	}
	
	
}
