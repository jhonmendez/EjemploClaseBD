package edu.cecar.controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelos.CompraDetalle;
import edu.cecar.modelos.CompraEncabezado;

public class ControladorCompraEncabezado {


	public void guardarCompra(CompraEncabezado compraEncabezado) throws SQLException {


		String cadenaDetalleCompra = "";

		for (CompraDetalle compraDetalle : compraEncabezado.getComprasDetalles()) 

			cadenaDetalleCompra +=  "-," + compraDetalle.getCodigoProducto() + "," +
					compraDetalle.getCantidad() +","+
					compraDetalle.getPrecio() + "," +
					compraDetalle.getSubtotal()+"@";


		String sql = "Call gestionarCompras(?,?)";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);

		ejecutarProcedimiento.setInt(1, compraEncabezado.getTotal());
		ejecutarProcedimiento.setString(2, cadenaDetalleCompra);
		ejecutarProcedimiento.execute();

	}


	public CompraEncabezado getCompraEncabezado(int identificador) throws SQLException {

		String sql = "select numero,fecha,total,codigoproducto,cantidad,precio,subtotal "
				+ "from comprasencabezado c, comprasdetalles cd "
				+ "where c.numero = cd.numerocompra "
				+ "and c.numero = ?";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);

		ejecutarProcedimiento.setInt(1, identificador);

		ResultSet resultado = ejecutarProcedimiento.executeQuery();

		CompraEncabezado compraEncabezado = null;
		List<CompraDetalle> comprasDetalles = new ArrayList<CompraDetalle>();


		while (resultado.next()) {


			if (compraEncabezado == null) {

				compraEncabezado = new CompraEncabezado();
				compraEncabezado.setTotal(resultado.getInt(3));
			}


			CompraDetalle compraDetalle = new CompraDetalle(resultado.getInt(4), resultado.getInt(5), 
					resultado.getInt(6), resultado.getInt(7));
			comprasDetalles.add(compraDetalle);


		}

		compraEncabezado.setComprasDetalles(comprasDetalles);

		return compraEncabezado;

	}


	public CompraEncabezado getUltimaCompraEncabezado() throws SQLException {

		String sql = "select max(numero) from comprasencabezado";

		PreparedStatement ejecutarProcedimiento = ConectarMySQL.getConexion().
				prepareStatement(sql);


		ResultSet resultado = ejecutarProcedimiento.executeQuery();
		resultado.next();
		return getCompraEncabezado(resultado.getInt(1));

	}

}
