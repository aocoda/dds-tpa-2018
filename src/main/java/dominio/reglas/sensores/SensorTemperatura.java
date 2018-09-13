package dominio.reglas.sensores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TEMPERATURA")
public class SensorTemperatura extends Sensor {

	@Override
	public double medir() {
		
		return 0;
	}
}