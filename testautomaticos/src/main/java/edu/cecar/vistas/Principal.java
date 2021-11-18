package edu.cecar.vistas;

import edu.cecar.componentes.ConectarMySQL;

public class Principal {
	
	public static void main(String[] args) {
		
		try {
			
			ConectarMySQL conectarMySQL = 
					new ConectarMySQL("127.0.0.1", "manpower", "root", "");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
