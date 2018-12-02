package dominio;

import java.util.List;
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
	
	@SuppressWarnings("unused")
	private Transformador() { }
	
	public List<Cliente> clientesAsociados(List<Cliente> clientes, List<Transformador> transformadores) {
		
		return clientes
				.stream()
				.filter(cliente -> cliente.transformadorAsociado(transformadores).equals(this))
				.collect(Collectors.toList());
	}

	public double consumoDe(Periodo unPeriodo, List<Cliente> clientes, List<Transformador> transformadores) {
		
		return clientesAsociados(clientes, transformadores)
				.stream()
				.mapToDouble(cliente -> cliente.consumoDe(unPeriodo))
				.sum();
	}
	
	public Coordenada getCoordenada() {
		
		return coordenada;
	}

	public String getNombre() {
		
		return nombre;
	}
}