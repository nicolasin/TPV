package es.nico.wata.tpv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ComponentesProductos")
public class ComponenteProducto {
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@OneToOne
	@JoinColumn(name="idComponente")
	Componente componente;
	@OneToOne
	@JoinColumn(name="idProducto")
	Producto producto;
	@Column(name="cantidad")
	double cantidad;
	
	public ComponenteProducto(){}
	public ComponenteProducto(Componente c, Producto p, double cantidad) {
		this.componente = c;
		this.producto = p;
		this.cantidad = cantidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Componente getComponente() {
		return componente;
	}
	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "ComponenteProducto [id=" + id + ", componente=" + componente.nombre + ", producto=" + producto.nombre + ", cantidad="
				+ cantidad + "]";
	}
	
}
