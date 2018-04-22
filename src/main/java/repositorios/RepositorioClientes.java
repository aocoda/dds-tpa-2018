package repositorios;

import dominio.Cliente;

public class RepositorioClientes extends RepositorioGenerico<Cliente> {
	
	private static RepositorioClientes instancia;		
		
	public static RepositorioClientes getInstancia() {
			
		if(instancia == null) {
	            
			instancia = new RepositorioClientes();
		}
		return instancia;
	}
}