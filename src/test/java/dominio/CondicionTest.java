package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.reglas.Sensor;
import dominio.reglas.condiciones.Condicion;
import dominio.reglas.condiciones.compuestas.Or;
import dominio.reglas.condiciones.simples.CondicionSimple;

public class CondicionTest {

	private Sensor sensorQueSiempreMide30Grados = new Sensor() {
		
		@Override
		public double medir() {

			return 30;
		}
	};
	
	private Sensor sensorQueSiempreMide60PorCientoDeHumedad = new Sensor() {
		
		@Override
		public double medir() {

			return 0.6;
		}
	};
	
	@Test
	public void conUnaCondicionSimpleDeTemperaturaMenorA30YunSensorQueMide30_LaCondicionNoSeDebeCumplir() {
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSimple(sensorQueSiempreMide30Grados) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion < 30;
			}
		};
		
		assertTrue(!condicionTemperaturaMenorA30.seCumple());
	}
	
	@Test
	public void conUnaCondicionSimpleDeTemperaturaEntre15Y40YunSensorQueMide30_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaEntre15Y40 = new CondicionSimple(sensorQueSiempreMide30Grados) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion > 15 && medicion < 40;
			}
		};
		
		assertTrue(condicionTemperaturaEntre15Y40.seCumple());
	}
	
	@Test
	public void conUnaCondicionOrDeTemperaturaMenorA30_o_HumedadIgualA60PorCiento_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSimple(sensorQueSiempreMide30Grados) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion < 30;
			}
		};
		
		Condicion condicionHumedadIgualA60PorCiento = new CondicionSimple(sensorQueSiempreMide60PorCientoDeHumedad) {
			
			@Override
			public boolean aplicarCriterio(double medicion) {
				
				return medicion == 0.6;
			}
		};
		
		Condicion condicionOr = new Or(condicionTemperaturaMenorA30, condicionHumedadIgualA60PorCiento);
		
		assertTrue(condicionOr.seCumple());
	}
}