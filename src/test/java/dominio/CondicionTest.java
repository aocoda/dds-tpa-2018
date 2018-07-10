package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.reglas.Sensor;
import dominio.reglas.condiciones.Condicion;
import dominio.reglas.condiciones.CondicionSensor;
import dominio.reglas.condiciones.compuestas.Or;
import dominio.reglas.condiciones.relaciones.Entre;
import dominio.reglas.condiciones.relaciones.IgualA;
import dominio.reglas.condiciones.relaciones.MenorA;

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
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSensor(sensorQueSiempreMide30Grados , new MenorA(30));
		
		assertTrue(!condicionTemperaturaMenorA30.seCumple());
	}
	
	@Test
	public void conUnaCondicionSimpleDeTemperaturaEntre15Y40YunSensorQueMide30_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaEntre15Y40 = new CondicionSensor(sensorQueSiempreMide30Grados, new Entre(15, 40));
		
		assertTrue(condicionTemperaturaEntre15Y40.seCumple());
	}
	
	@Test
	public void conUnaCondicionOrDeTemperaturaMenorA30_o_HumedadIgualA60PorCiento_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSensor(sensorQueSiempreMide30Grados, new MenorA(30));
		
		Condicion condicionHumedadIgualA60PorCiento = new CondicionSensor(sensorQueSiempreMide60PorCientoDeHumedad, new IgualA(0.6));
		
		Condicion condicionOr = new Or(condicionTemperaturaMenorA30, condicionHumedadIgualA60PorCiento);
		
		assertTrue(condicionOr.seCumple());
	}
}