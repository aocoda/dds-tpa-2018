package dominio.reglas.condiciones.compuestas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.reglas.condiciones.Condicion;

@Entity
@DiscriminatorValue(value = "AND")
public class And extends CondicionCompuesta {

	public And(Condicion unaCondicion, Condicion otraCondicion) {
		
		super(unaCondicion, otraCondicion);
	}
	
	@SuppressWarnings("unused")
	private And() { }

	@Override
	public boolean seCumple() {
		
		return unaCondicion.seCumple() && otraCondicion.seCumple();
	}
}