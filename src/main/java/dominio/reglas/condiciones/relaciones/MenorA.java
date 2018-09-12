package dominio.reglas.condiciones.relaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MENOR_A")
public class MenorA extends Relacion {

	private double unValor;

	public MenorA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor < unValor;
	}
}