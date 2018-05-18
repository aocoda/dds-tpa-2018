package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.reglas.sensores.condiciones.Condicion;
import dominio.reglas.sensores.sensores.Sensor;

public class CondicionTest {

	private Sensor sensorQueSiempreMide30Grados = new Sensor() {
		
		@Override
		public double medir() {

			return 30;
		}
	};
	
	@Test
	public void conUnaCondicionDeTemperaturaMenorA30YunSensorQueMide30_LaCondicionNoSeDebeCumplir() {
		
		Condicion condicionTemperaturaMenorA30 = new Condicion(sensorQueSiempreMide30Grados) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion < 30;
			}
		};
		
		assertTrue(!condicionTemperaturaMenorA30.seCumple());
	}
	
	@Test
	public void conUnaCondicionDeTemperaturaEntre15Y40YunSensorQueMide30_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaEntre15Y40 = new Condicion(sensorQueSiempreMide30Grados) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion > 15 && medicion < 40;
			}
		};
		
		assertTrue(condicionTemperaturaEntre15Y40.seCumple());
	}
}