package dominio.dispositivos.inteligentes;

import java.time.LocalDateTime;

import dominio.dispositivos.*;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;

public class Uso PeriodoUtils {

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

		LocalDateTime nuevoInicio = maximo(periodo.getFechaYHoraDeInicio(), unPeriodo.getFechaYHoraDeInicio());
		
		LocalDateTime nuevoFin = minimo(periodo.getFechaYHoraDeFin(), unPeriodo.getFechaYHoraDeFin());
		
		return new Uso(new Periodo(nuevoInicio, nuevoFin), estadoDispositivo);
	}
	
	public Periodo getPeriodo() {

		return periodo;
	}
}
