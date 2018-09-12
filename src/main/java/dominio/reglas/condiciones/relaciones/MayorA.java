package dominio.reglas.condiciones.relaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MAYOR_A")
public class MayorA extends Relacion {

	private double unValor;

	public MayorA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor > unValor;
	}
}