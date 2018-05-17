package dominio.dispositivos.inteligentes;

import dominio.dispositivos.*;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;

public class Uso implements Comparable<Uso>, PeriodoUtils {

	private Periodo periodo;
	private EstadoDispositivo estadoDispositivo;

	public Uso(Periodo periodo, EstadoDispositivo estadoDispositivo) {

		this.periodo = periodo;
		this.estadoDispositivo = estadoDispositivo;
	}

	public double consumo(double consumoPorHoraDelDispositivo) {

		return consumoPorHoraDelDispositivo * periodo.cantidadDeHoras();
	}

	public Uso acotarExtremos(Periodo unPeriodo) {

		return new Uso(new Periodo(maximo(periodo.getFechaYHoraDeInicio(), unPeriodo.getFechaYHoraDeInicio()),
				minimo(periodo.getFechaYHoraDeFin(), unPeriodo.getFechaYHoraDeFin())), estadoDispositivo);
	}

	@Override
	public int compareTo(Uso otroUso) {

		return periodo.compareTo(otroUso.getPeriodo());
	}

	public Periodo getPeriodo() {

		return periodo;
	}

	public EstadoDispositivo getModo() {

		return estadoDispositivo;
	}
}