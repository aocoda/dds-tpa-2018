package dominio.reglas.condiciones.compuestas;

import dominio.reglas.condiciones.Condicion;

public class Or extends CondicionCompuesta {

	public Or(Condicion unaCondicion, Condicion otraCondicion) {
		
		super(unaCondicion, otraCondicion);
	}

	@Override
	public boolean seCumple() {
		
		return unaCondicion.seCumple() || otraCondicion.seCumple();
	}
}
