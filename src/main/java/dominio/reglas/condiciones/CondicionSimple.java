package dominio.reglas.condiciones;

import dominio.reglas.Sensor;

public abstract class CondicionSimple implements Condicion {

	private Sensor sensor;

	public CondicionSimple(Sensor sensor) {
		
		this.sensor = sensor;
	}

	@Override
	public boolean seCumple() {
		
		return aplicarCriterio(sensor.medir());
	}
	
	public abstract boolean aplicarCriterio(double medicion);
}