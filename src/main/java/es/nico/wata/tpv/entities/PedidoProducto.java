package es.nico.wata.tpv.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="PedidosProductos")
public class PedidoProducto {
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	@Column(name="Cantidad")
	double cantidad;
	@OneToOne
	@JoinColumn(name="idProducto")
	Producto producto;
	@OneToOne
	@JoinColumn(name="idPedido")
	Pedido pedido;
	public PedidoProducto() {
		
	}
	public PedidoProducto(Pedido pedido ,Producto producto, double cantidad) {
		this.producto = producto;
		this.pedido = pedido;
		this.cantidad = cantidad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "Pedido: "+pedido.getId() +", Producto: "+ producto.getNombre()+" ["+cantidad+ "]";
	}
	
	
}
