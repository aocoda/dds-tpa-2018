package repositorios;

import dominio.reglas.sensores.Sensor;

public class RepositorioSensores extends RepositorioGenerico<Sensor> {

	@Override
	protected Class<Sensor> getClase() {
		
		return Sensor.class;
	}
}