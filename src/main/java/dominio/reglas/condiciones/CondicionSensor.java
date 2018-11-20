package dominio.reglas.condiciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import dominio.reglas.sensores.Sensor;

@Entity
@DiscriminatorValue(value = "CONDICION_SENSOR")
public class CondicionSensor extends Condicion {

	@ManyToOne
	private Sensor sensor;
	@Enumerated(value = EnumType.STRING)
	private Relacion relacion;
	private double valor;

	public CondicionSensor(Sensor sensor, Relacion relacion, double valor) {
		
		this.sensor = sensor;
		this.relacion = relacion;
		this.valor = valor;
	}
	
	@SuppressWarnings("unused")
	private CondicionSensor() { }

	@Override
	public boolean seCumple() {
		
		return relacion.aplicarCon(valor, sensor.medir());
	}
}