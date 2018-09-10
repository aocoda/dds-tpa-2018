package dominio;

import java.util.Collection;
import java.util.stream.Collectors;

import dominio.Transformador;
import dominio.dispositivos.Periodo;
import dominio.geoposicionamiento.Area;

public class Zona {
	
	private String nombre;
	private Area area;

	public Zona(String nombre, Area area) {
		
		this.nombre = nombre;
		this.area = area;
	}

	public Collection<Transformador> transformadoresAsociados(Collection<Transformador> transformadores) {
		
		return transformadores
				.stream()
				.filter(transformador -> area.contieneA(transformador.getCoordenada()))
				.collect(Collectors.toSet());
	}
	
	public double consumoDe(Periodo unPeriodo, Collection<Cliente> clientes, Collection<Transformador> transformadores) {
		
		return transformadoresAsociados(transformadores)
				.stream()
				.mapToDouble(transformador -> transformador.consumoDe(unPeriodo, clientes, transformadores))
				.sum();
	}
}