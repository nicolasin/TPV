package es.nico.wata.tpv.entities;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Pedidos")
public class Pedido {
	@Id
	@GeneratedValue
	@Column(name="id")
	long id;
	@Column(name="fecha")
	LocalDate fecha;
	@Column(name="total")
	double total;
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
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", total=" + total + ", mesa=" + mesa + ", formadePago="
				+ "formadePago" + ", estaPagado=" + estaPagado + ", descuento=" + "descuento" + ", Descripccion="
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
	
	
}
