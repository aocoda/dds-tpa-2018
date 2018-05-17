package dominio.dispositivos;

import java.time.Duration;
import java.time.LocalDateTime;

public class Periodo implements Comparable<Periodo> {

	private LocalDateTime fechaYHoraDeInicio;
	private LocalDateTime fechaYHoraDeFin;

	public Periodo(LocalDateTime fechaYHoraDeInicio, LocalDateTime fechaYHoraDeFin) {

		this.fechaYHoraDeInicio = fechaYHoraDeInicio;
		this.fechaYHoraDeFin = fechaYHoraDeFin;
	}

	public double cantidadDeHoras() {
		
		return Duration.between(fechaYHoraDeInicio, fechaYHoraDeFin).toMinutes() / 60;
	}
	
	public double cantidadDeDias() {
		
		return cantidadDeHoras() / 24;
	}
	
	public boolean contiene(Periodo otroPeriodo) {
		
		return otroPeriodo.getFechaYHoraDeInicio().isBefore(fechaYHoraDeFin) 
				&& otroPeriodo.getFechaYHoraDeFin().isAfter(fechaYHoraDeInicio);
	}

	public static Periodo deLasUltimasNHoras(double nHoras) {
		
		LocalDateTime fechaYHoraDeFin = LocalDateTime.now();
		
		LocalDateTime fechaYHoraDeInicio = fechaYHoraDeFin.minusMinutes(Double.valueOf(nHoras * 60).longValue());
		
		return new Periodo(fechaYHoraDeInicio, fechaYHoraDeFin);
	}
	
	public static Periodo deLosUltimosNMeses(int nMeses) {
		
		LocalDateTime fechaYHoraDeFin = LocalDateTime.now();
		
		LocalDateTime fechaYHoraDeInicio = fechaYHoraDeFin.minusMonths(nMeses);
		
		return new Periodo(fechaYHoraDeInicio, fechaYHoraDeFin);
	}
	
	@Override
	public int compareTo(Periodo otroPeriodo) {
		
		return fechaYHoraDeInicio.compareTo(otroPeriodo.getFechaYHoraDeInicio());
	}
	
	public LocalDateTime getFechaYHoraDeFin() {
		
		return fechaYHoraDeFin;
	}
	
	public LocalDateTime getFechaYHoraDeInicio() {
		
		return fechaYHoraDeInicio;
	}
}