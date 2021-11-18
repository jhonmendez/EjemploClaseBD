package edu.cecar.modelos;

import java.sql.Date;
import java.util.List;

public class CompraEncabezado {
	
	private int numero;
	private Date fecha;
	private int total;
	private List<CompraDetalle> comprasDetalles;
	
	public CompraEncabezado() {
	}
	
	public CompraEncabezado(int numero, Date fecha, int total) {
		
		this.numero = numero;
		this.fecha = fecha;
		this.total = total;
		
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CompraDetalle> getComprasDetalles() {
		return comprasDetalles;
	}

	public void setComprasDetalles(List<CompraDetalle> comprasDetalles) {
		this.comprasDetalles = comprasDetalles;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		CompraEncabezado compraEncabezado = (CompraEncabezado)obj;
		
		return compraEncabezado.getTotal() == total && isIgualDetalle(compraEncabezado.getComprasDetalles(),comprasDetalles);
	}
	
	
	private boolean isIgualDetalle(List<CompraDetalle> comprasDetalles, List<CompraDetalle> comprasDetallesUno) {
		
		
		boolean sw = comprasDetalles.size() == comprasDetallesUno.size();
		
		for (int i = 0; i < comprasDetalles.size() && sw; i++) {

			CompraDetalle compraDetalle = comprasDetalles.get(i);
			CompraDetalle compraDetalleuno = comprasDetallesUno.get(i);
			
			sw = compraDetalle.getCodigoProducto() == compraDetalle.getCodigoProducto() &&
					compraDetalle.getCantidad() == compraDetalle.getCantidad() &&
					compraDetalle.getPrecio() == compraDetalle.getPrecio() &&
					compraDetalle.getSubtotal() == compraDetalle.getSubtotal() ;

		}
		
		return sw;
		
	}
	
  
}
