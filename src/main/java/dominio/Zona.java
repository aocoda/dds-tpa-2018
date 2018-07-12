package dominio;

import java.util.Collection;
import dominio.Transformador;
import dominio.dispositivos.Periodo;

public class Zona {
	
	private String nombre;
	private Collection<Transformador> transformadores;

	public Zona(String nombre, Collection<Transformador> transformadores) {
		
		this.nombre = nombre;
		this.transformadores = transformadores;
	}

	public double consumoDe(Periodo unPeriodo) {
		
		return transformadores
				.stream()
				.mapToDouble(transformador -> transformador.consumoDe(unPeriodo))
				.sum();
	}
}