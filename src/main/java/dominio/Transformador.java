package dominio;

import java.util.Collection;
import dominio.Cliente;
import dominio.dispositivos.Periodo;

public class Transformador {

	private Collection<Cliente> clientes;

	public Transformador(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Collection<Cliente> getClientes() {
		return clientes;
	}

	public void addCliente(Cliente unCliente) {
		clientes.add(unCliente);
	}

	public double consumoDe(Periodo unPeriodo) {
		return clientes
				.stream()
				.map(cliente -> cliente.consumoDe(unPeriodo))
				.mapToInt(Double::intValue)
				.sum();
	}
}