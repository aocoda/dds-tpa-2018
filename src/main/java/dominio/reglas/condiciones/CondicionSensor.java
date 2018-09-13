package dominio.reglas.condiciones;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dominio.reglas.condiciones.relaciones.Relacion;
import dominio.reglas.sensores.Sensor;

@Entity
public class CondicionSensor extends Condicion {

	@ManyToOne
	private Sensor sensor;
	@OneToOne
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