package dominio.reglas.reglas;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.reglas.sensores.condiciones.Condicion;
import dominio.reglas.sensores.condiciones.OtroTipoCondicion;
import dominio.reglas.sensores.sensores.Sensor;

public class CondicionTEst {

	@Test
	public void test() {

		Sensor unSensor = new Sensor() {
			
			@Override
			public double medir() {
			
				return 100;
			}
		};	
		
		Condicion c = new Condicion(unSensor, temperatura -> temperatura == 30);
		
		OtroTipoCondicion c2 = new OtroTipoCondicion(unSensor, 30);
	}

}
