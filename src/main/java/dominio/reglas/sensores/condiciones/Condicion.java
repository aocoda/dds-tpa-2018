package dominio.reglas.sensores.condiciones;

import java.util.function.Predicate;

import dominio.reglas.sensores.sensores.Sensor;

public class Condicion {

	private Sensor sensor;
	private Predicate<Double> criterio;

	public Condicion(Sensor sensor, Predicate<Double> criterio) {
		
		this.sensor = sensor;
		this.criterio = criterio;
	}
	
	public boolean seCumple() {
		
		return criterio.test(sensor.medir());
	}
}