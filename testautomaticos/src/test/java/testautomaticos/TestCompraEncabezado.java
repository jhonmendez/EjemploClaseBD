package testautomaticos;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controladores.ControladorCompraEncabezado;
import edu.cecar.modelos.CompraDetalle;
import edu.cecar.modelos.CompraEncabezado;

public class TestCompraEncabezado {


	public TestCompraEncabezado() {

		try {

			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "ejemploclase", "root", "");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Test
	public void testGestionarCompras() {

		try {

			CompraDetalle compraDetalle = new CompraDetalle(90, 4, 40000, 160000);
			CompraDetalle compraDetalle1 = new CompraDetalle(91, 3, 30000, 90000);
			CompraDetalle compraDetalle2 = new CompraDetalle(92, 2, 50000, 100000);

			List<CompraDetalle> detalles = new ArrayList<CompraDetalle>();
			detalles.add(compraDetalle);
			detalles.add(compraDetalle1);
			detalles.add(compraDetalle2);

			CompraEncabezado compraEncabezado = new CompraEncabezado();
			compraEncabezado.setTotal(350000);
			compraEncabezado.setComprasDetalles(detalles);

			ControladorCompraEncabezado controladorCompraEncabezado = new ControladorCompraEncabezado();
			controladorCompraEncabezado.guardarCompra(compraEncabezado);
			
			CompraEncabezado compraEncabezadoBD = controladorCompraEncabezado.getUltimaCompraEncabezado();
			
			assertEquals("El dato a insertar no coincide con el insertado",compraEncabezado,compraEncabezadoBD);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
