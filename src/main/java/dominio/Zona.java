package dominio;

import java.util.Collection;
import dominio.Transformador;
import dominio.dispositivos.Periodo;

public class Zona {
	Collection<Transformador> transformadores;

	public Zona(Collection<Transformador> transformadores) {
		this.transformadores = transformadores;
	}

	public void addTransformador(Transformador transformador) {
		transformadores.add(transformador);
	}

	public void removeTransformador(Transformador transformador) {
		transformadores.remove(transformador);
	}

	public double consumoDe(Periodo unPeriodo) {
		return transformadores
				.stream()
				.map(transformador -> transformador.consumoDe(unPeriodo))
				.mapToDouble(Double::doubleValue)
				.sum();
	}
}
