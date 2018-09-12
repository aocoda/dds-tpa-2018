package dominio.reglas.condiciones.relaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ENTRE")
public class Entre extends Relacion {

	private double unValor;
	private double otroValor;
	
	public Entre(double unValor, double otroValor) {
		
		this.unValor = unValor;
		this.otroValor = otroValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return unValor < valor && valor < otroValor;
	}
}