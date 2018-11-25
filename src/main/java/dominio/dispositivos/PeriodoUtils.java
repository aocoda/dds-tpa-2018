package dominio.dispositivos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;

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

	public static Periodo delMesYAnio(int mes, int anio) {
		
		LocalDateTime fechaYHoraDeInicio = YearMonth.of(anio, mes).atDay(1).atTime(LocalTime.MIN);
		
		LocalDateTime fechaYHoraDeFin = YearMonth.of(anio, mes).atEndOfMonth().atTime(LocalTime.MAX);
		
		return new Periodo(fechaYHoraDeInicio, fechaYHoraDeFin);
	}

	public static Periodo delMes(int mes) {
		
		int anioActual = Year.now().getValue();
		
		return delMesYAnio(mes, anioActual);
	}
	
	public static Periodo delMesActual() {
		
		int mesActual = YearMonth.now().getMonthValue();
		
		return delMes(mesActual);
	}
}