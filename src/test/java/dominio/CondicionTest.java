package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.reglas.condiciones.Condicion;
import dominio.reglas.condiciones.CondicionSensor;
import dominio.reglas.condiciones.Relacion;
import dominio.reglas.condiciones.compuestas.And;
import dominio.reglas.condiciones.compuestas.Or;
import dominio.reglas.sensores.Sensor;

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
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSensor(sensorQueSiempreMide30Grados , Relacion.MENOR_A, 30);
		
		assertTrue(!condicionTemperaturaMenorA30.seCumple());
	}
	
	@Test
	public void conUnaCondicionAndDeTemperaturaEntre15Y40YunSensorQueMide30_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaMayorA15 = new CondicionSensor(sensorQueSiempreMide30Grados, Relacion.MAYOR_A, 15);
		
		Condicion condicionTemperaturaMenorA40 = new CondicionSensor(sensorQueSiempreMide30Grados, Relacion.MENOR_A, 40);
		
		Condicion condicionAnd = new And(condicionTemperaturaMayorA15, condicionTemperaturaMenorA40);
		
		assertTrue(condicionAnd.seCumple());
	}
	
	@Test
	public void conUnaCondicionOrDeTemperaturaMenorA30_o_HumedadIgualA60PorCiento_LaCondicionSeDebeCumplir() {
		
		Condicion condicionTemperaturaMenorA30 = new CondicionSensor(sensorQueSiempreMide30Grados, Relacion.MENOR_A, 30);
		
		Condicion condicionHumedadIgualA60PorCiento = new CondicionSensor(sensorQueSiempreMide60PorCientoDeHumedad, Relacion.IGUAL_A, 0.6);
		
		Condicion condicionOr = new Or(condicionTemperaturaMenorA30, condicionHumedadIgualA60PorCiento);
		
		assertTrue(condicionOr.seCumple());
	}
}