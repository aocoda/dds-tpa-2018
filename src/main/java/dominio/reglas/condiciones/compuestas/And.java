package dominio.reglas.condiciones.compuestas;

import dominio.reglas.condiciones.Condicion;

public class And extends CondicionCompuesta {

	public And(Condicion unaCondicion, Condicion otraCondicion) {
		
		super(unaCondicion, otraCondicion);
	}

	@Override
	public boolean seCumple() {
		
		return unaCondicion.seCumple() && otraCondicion.seCumple();
	}
}