package dominio.reglas.sensores.condiciones;

import dominio.reglas.sensores.sensores.Sensor;

public class OtroTipoCondicion {

	private Sensor sensor;
	private double variableDeComparacion;

	public OtroTipoCondicion(Sensor sensor, double variableDeComparacion) {
		
		this.sensor = sensor;
		this.variableDeComparacion = variableDeComparacion;
		/*
		 * podria tener mas variables, tengo todo el control del algoritmo aca.
		 * 
		 * se podria tener mas de una variable de comparacion
		 */
	}
	
	//aca sobreescribiria el metodo, ya que estaria usando herencia y condicion seria abstracta
	public boolean seCumple() {
		
		return sensor.medir() < variableDeComparacion;
	}
}