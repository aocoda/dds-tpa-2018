package dominio;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import dominio.Cliente;
import dominio.dispositivos.Periodo;
import dominio.geoposicionamiento.Coordenada;
import repositorios.EntidadPersistente;

@Entity
public class Transformador extends EntidadPersistente {

	private String nombre;
	@Embedded
	private Coordenada coordenada;

	public Transformador(String nombre, Coordenada coordenada) {
		
		this.nombre = nombre;
		this.coordenada = coordenada;
	}
	
	public Collection<Cliente> clientesAsociados(Collection<Cliente> clientes, Collection<Transformador> transformadores) {
		
		return clientes
				.stream()
				.filter(cliente -> cliente.transformadorAsociado(transformadores).equals(this))
				.collect(Collectors.toSet());
	}

	public double consumoDe(Periodo unPeriodo, Collection<Cliente> clientes, Collection<Transformador> transformadores) {
		
		return clientesAsociados(clientes, transformadores)
				.stream()
				.mapToDouble(cliente -> cliente.consumoDe(unPeriodo))
				.sum();
	}
	
	public Coordenada getCoordenada() {
		
		return coordenada;
	}
}