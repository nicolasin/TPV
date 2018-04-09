package es.nico.wata.tpv.entities;

public class PedidoProducto {
	Long id;
	double cantidad;
	Producto producto;
	Pedido pedido;
	public PedidoProducto() {
		
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
		return "PedidoProducto [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", pedido=" + pedido
				+ "]";
	}
	
	
}
