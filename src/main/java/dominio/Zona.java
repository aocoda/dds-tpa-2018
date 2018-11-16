package dominio;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import dominio.Transformador;
import dominio.dispositivos.Periodo;
import dominio.geoposicionamiento.Area;
import repositorios.EntidadPersistente;

@Entity
public class Zona extends EntidadPersistente {
	
	private String nombre;
	@Embedded
	private Area area;

	public Zona(String nombre, Area area) {
		
		this.nombre = nombre;
		this.area = area;
	}
	
	@SuppressWarnings("unused")
	private Zona() { }

	public List<Transformador> transformadoresAsociados(List<Transformador> transformadores) {
		
		return transformadores
				.stream()
				.filter(transformador -> area.contieneA(transformador.getCoordenada()))
				.collect(Collectors.toList());
	}
	
	public double consumoDe(Periodo unPeriodo, List<Cliente> clientes, List<Transformador> transformadores) {
		
		return transformadoresAsociados(transformadores)
				.stream()
				.mapToDouble(transformador -> transformador.consumoDe(unPeriodo, clientes, transformadores))
				.sum();
	}
}