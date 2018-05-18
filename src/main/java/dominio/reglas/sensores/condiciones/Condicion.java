package dominio.reglas.sensores.condiciones;

import dominio.reglas.sensores.sensores.Sensor;

public abstract class Condicion {

	private Sensor sensor;

	public Condicion(Sensor sensor) {
		
		this.sensor = sensor;
	}
	
	public boolean seCumple() {
		
		return aplicarCriterio(sensor.medir());
	}
	
	public abstract boolean aplicarCriterio(double medicion);
}