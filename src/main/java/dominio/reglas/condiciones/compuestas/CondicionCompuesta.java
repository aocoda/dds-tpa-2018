package dominio.reglas.condiciones.compuestas;

import javax.persistence.CascadeType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import dominio.reglas.condiciones.Condicion;

@MappedSuperclass
public abstract class CondicionCompuesta extends Condicion {

	@OneToOne(cascade = CascadeType.ALL)
	protected Condicion unaCondicion;
	@OneToOne(cascade = CascadeType.ALL)
	protected Condicion otraCondicion;
	
	public CondicionCompuesta(Condicion unaCondicion, Condicion otraCondicion) {
		
		this.unaCondicion = unaCondicion;
		this.otraCondicion = otraCondicion;
	}
}