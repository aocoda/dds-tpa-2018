package dominio;

import java.util.Collection;
import dominio.Transformador;
import dominio.dispositivos.Periodo;

public class Zona {
	
	private Collection<Transformador> transformadores;

	public Zona(Collection<Transformador> transformadores) {
		
		this.transformadores = transformadores;
	}

	public void agregarTransformador(Transformador transformador) {
		
		transformadores.add(transformador);
	}

	public void eliminarTransformador(Transformador transformador) {
		
		transformadores.remove(transformador);
	}

	public double consumoDe(Periodo unPeriodo) {
		
		return transformadores
				.stream()
				.mapToDouble(transformador -> transformador.consumoDe(unPeriodo))
				.sum();
	}
}