package dominio.reglas.condiciones.compuestas;

import dominio.reglas.condiciones.Condicion;

public abstract class CondicionCompuesta implements Condicion {

	protected Condicion unaCondicion;
	protected Condicion otraCondicion;
	
	public CondicionCompuesta(Condicion unaCondicion, Condicion otraCondicion) {
		
		this.unaCondicion = unaCondicion;
		this.otraCondicion = otraCondicion;
	}
}