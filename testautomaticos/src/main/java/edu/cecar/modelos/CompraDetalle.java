package edu.cecar.modelos;

public class CompraDetalle {
	
	private int codigoProducto;
	private int cantidad;
	private float precio;
	private int subtotal;
	
	public CompraDetalle(int codigoProducto, int cantidad, int precio, int subtotal) {
		
		this.codigoProducto = codigoProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	
	
	

}
