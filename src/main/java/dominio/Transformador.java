package dominio;

import java.util.Collection;
import dominio.Cliente;
import dominio.dispositivos.Periodo;

public class Transformador {

	private String nombre;
	private Collection<Cliente> clientes;

	public Transformador(String nombre, Collection<Cliente> clientes) {
		this.nombre = nombre;
		this.clientes = clientes;
	}

	public double consumoDe(Periodo unPeriodo) {
		
		return clientes
				.stream()
				.mapToDouble(cliente -> cliente.consumoDe(unPeriodo))
				.sum();
	}
}