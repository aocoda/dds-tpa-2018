package dominio.dispositivos.inteligentes;

import java.util.Collection;

import dominio.dispositivos.Periodo;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;

public class Uso implements Comparable<Uso>, StreamUtils<Uso> {

	private Periodo periodo;
	private EstadoDispositivo estadoDispositivo;

	public Uso(Periodo periodo, EstadoDispositivo estadoDispositivo) {

		this.periodo = periodo;
		this.estadoDispositivo = estadoDispositivo;
	}

	public double consumo(double consumoPorHoraDelDispositivo) {
		
		return consumoPorHoraDelDispositivo * periodo.cantidadDeHoras();
	}

	public Uso acotarExtremos(Collection<Uso> usos, Periodo unPeriodo) {
		
		if(esElPrimeroDeLaLista(this, usos))
			return new Uso(periodo.acotarSiEsPrimero(unPeriodo), estadoDispositivo);
		
		else if(esElUltimoDeLaLista(this, usos))
			return new Uso(periodo.acotarSiEsUltimo(unPeriodo), estadoDispositivo);
		
		else
			return this;
	}
	
	@Override
	public int compareTo(Uso otroUso) {
		
		return periodo.compareTo(otroUso.getPeriodo());
	}
	
	public Periodo getPeriodo() {
		
		return periodo;
	}
}