package repositorios;

import dominio.Cliente;

public class RepositorioClientes extends RepositorioGenerico<Cliente> {

	public void ejecutarReglasQueCorrespondan() {
		elementos.forEach(c -> c.ejecutarReglasQueCorrespondan());
	}
}