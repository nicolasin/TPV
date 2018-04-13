package es.nico.wata.tpv.entities;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	long id;
	@Column(name="fecha")
	LocalDate fecha;
	@Column(name="total")
	double total;
	@OneToMany(mappedBy="producto")
	Set<PedidoProducto> productospedido = new HashSet<>();
	@Column(name="pagado")
	boolean estaPagado;
	@Column(name="descripcion")
	String descripccion;
	@OneToOne
	@JoinColumn(name="idMesa")
	Mesa mesa;
	
	@OneToOne
	@JoinColumn(name="idFormaPago")
	FormaDePago formadePago;
	
	@OneToOne
	@JoinColumn(name="idDescuento")
	Descuento descuento;
	
	public Pedido() {
	}

	public Set<PedidoProducto> getProductospedido() {
		return productospedido;
	}

	public void setProductospedido(Set<PedidoProducto> productospedido) {
		this.productospedido = productospedido;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", total=" + total + ", "
				+ "\n mesa=" + mesa.getNombre() + ", formadePago="
				+ formadePago.getNombre() + ", estaPagado=" + estaPagado + ", "
						+ "\n descuento=" + descuento.getNombre() + ", Descripccion="
				+ descripccion + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	public FormaDePago getFormadePago() {
		return formadePago;
	}
	public void setFormadePago(FormaDePago formadePago) {
		this.formadePago = formadePago;
	}
	public boolean isEstaPagado() {
		return estaPagado;
	}
	public void setEstaPagado(boolean estaPagado) {
		this.estaPagado = estaPagado;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	public String getDescripccion() {
		return descripccion;
	}
	public void setDescripccion(String descripccion) {
		this.descripccion = descripccion;
	}
	public void addProducto(Producto p, double cantidad) {
		boolean encontrado = false;
		for(PedidoProducto x: this.productospedido) {
			if(x.getProducto().equals(p)) {
				x.setCantidad(x.getCantidad()+cantidad);
				encontrado = true;
			}
		}
		if(!encontrado) {
			this.productospedido.add(new PedidoProducto(this, p, cantidad));
		}
	}
	public void removeProducto(Producto p, double cantidad) {
		for(PedidoProducto x: this.productospedido) {
			if(x.getProducto().equals(p)) {
				x.setCantidad(x.getCantidad()-cantidad);
				if(x.getCantidad()==0) {
					productospedido.remove(x);
				}
			}
		}
	}
	
}
