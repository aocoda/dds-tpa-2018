package dominio.dispositivos;

import java.time.LocalDateTime;

public interface PeriodoUtils {

	public static LocalDateTime maximo(LocalDateTime unaFecha, LocalDateTime otraFecha) {
		
		return unaFecha.isAfter(otraFecha) ? unaFecha : otraFecha;
	}

	public static LocalDateTime minimo(LocalDateTime unaFecha, LocalDateTime otraFecha) {
		
		return unaFecha.isBefore(otraFecha) ? unaFecha : otraFecha;
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
}