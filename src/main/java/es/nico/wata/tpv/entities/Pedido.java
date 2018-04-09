package es.nico.wata.tpv.entities;
import java.time.LocalDate;
public class Pedido {
	long id;
	LocalDate fecha;
	double total;
	Mesa mesa;
	FormaDePago formadePago;
	boolean estaPagado;
	
	Descuento descuento;
	String Descripccion;
	
	public Pedido() {
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", total=" + total + ", mesa=" + mesa + ", formadePago="
				+ formadePago + ", estaPagado=" + estaPagado + ", descuento=" + descuento + ", Descripccion="
				+ Descripccion + "]";
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
		return Descripccion;
	}
	public void setDescripccion(String descripccion) {
		Descripccion = descripccion;
	}
	
	
}
