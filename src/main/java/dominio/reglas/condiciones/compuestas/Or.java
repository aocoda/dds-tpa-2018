package dominio.reglas.condiciones.compuestas;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.reglas.condiciones.Condicion;

@Entity
@DiscriminatorValue(value = "OR")
public class Or extends CondicionCompuesta {

	public Or(Condicion unaCondicion, Condicion otraCondicion) {
		
		super(unaCondicion, otraCondicion);
	}

	@Override
	public boolean seCumple() {
		
		return unaCondicion.seCumple() || otraCondicion.seCumple();
	}
}
