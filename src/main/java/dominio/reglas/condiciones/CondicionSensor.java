package dominio.reglas.condiciones;

import dominio.reglas.Sensor;
import dominio.reglas.condiciones.comparaciones.Relacion;

public class CondicionSensor implements Condicion {

	private Sensor sensor;
	private Relacion comparacion;

	public CondicionSensor(Sensor sensor, Relacion comparacion) {
		
		this.sensor = sensor;
		this.comparacion = comparacion;
	}

	@Override
	public boolean seCumple() {
		
		return comparacion.aplicarCon(sensor.medir());
	}
}