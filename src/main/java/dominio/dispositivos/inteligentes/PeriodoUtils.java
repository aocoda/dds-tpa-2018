package dominio.dispositivos.inteligentes;

import java.time.LocalDateTime;

public interface PeriodoUtils {

	default public LocalDateTime maximo(LocalDateTime unaFecha, LocalDateTime otraFecha) {
		return unaFecha.isAfter(otraFecha) ? unaFecha : otraFecha;
	}

	default public LocalDateTime minimo(LocalDateTime unaFecha, LocalDateTime otraFecha) {
		return unaFecha.isBefore(otraFecha) ? unaFecha : otraFecha;
	}
}