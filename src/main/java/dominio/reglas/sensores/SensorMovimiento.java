package dominio.reglas.sensores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MOVIMIENTO")
public class SensorMovimiento extends Sensor {

	@Override
	public double medir() {
		
		return 0;
	}
}