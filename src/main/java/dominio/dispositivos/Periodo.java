package dominio.dispositivos;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

@Embeddable
public class Periodo {

	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fechaYHoraDeInicio;
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime fechaYHoraDeFin;

	public Periodo(LocalDateTime fechaYHoraDeInicio, LocalDateTime fechaYHoraDeFin) {

		this.fechaYHoraDeInicio = fechaYHoraDeInicio;
		this.fechaYHoraDeFin = fechaYHoraDeFin;
	}
	
	@SuppressWarnings("unused")
	private Periodo() { }

	public double cantidadDeHoras() {
		
		return (double) Duration.between(fechaYHoraDeInicio, fechaYHoraDeFin).toMinutes() / 60;
	}
	
	public double cantidadDeDias() {
		
		return cantidadDeHoras() / 24;
	}
	
	public boolean contiene(Periodo otroPeriodo) {
		
		return otroPeriodo.getFechaYHoraDeInicio().isBefore(fechaYHoraDeFin) 
				&& otroPeriodo.getFechaYHoraDeFin().isAfter(fechaYHoraDeInicio);
	}
	
	public LocalDateTime getFechaYHoraDeFin() {
		
		return fechaYHoraDeFin;
	}
	
	public LocalDateTime getFechaYHoraDeInicio() {
		
		return fechaYHoraDeInicio;
	}
}