package dominio.reglas.condiciones;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dominio.reglas.condiciones.relaciones.Relacion;
import dominio.reglas.sensores.Sensor;

@Entity
@DiscriminatorValue(value = "CONDICION_SENSOR")
public class CondicionSensor extends Condicion {

	@ManyToOne
	private Sensor sensor;
	@OneToOne(cascade = CascadeType.ALL)
	private Relacion relacion;

	public CondicionSensor(Sensor sensor, Relacion relacion) {
		
		this.sensor = sensor;
		this.relacion = relacion;
	}

	@Override
	public boolean seCumple() {
		
		return relacion.aplicarCon(sensor.medir());
	}
}