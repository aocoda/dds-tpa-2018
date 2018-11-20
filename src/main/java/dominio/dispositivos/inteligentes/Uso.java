package dominio.dispositivos.inteligentes;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import dominio.dispositivos.*;
import repositorios.EntidadPersistente;

@Entity
public class Uso extends EntidadPersistente implements PeriodoUtils {

	@Embedded
	private Periodo periodo;
	@Enumerated(value = EnumType.STRING)
	private EstadoDispositivo estadoDispositivo;

	public Uso(Periodo periodo, EstadoDispositivo estadoDispositivo) {

		this.periodo = periodo;
		this.estadoDispositivo = estadoDispositivo;
	}
	
	@SuppressWarnings("unused")
	private Uso() { }

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
	
	public EstadoDispositivo getEstadoDispositivo() {
		
		return estadoDispositivo;
	}
}