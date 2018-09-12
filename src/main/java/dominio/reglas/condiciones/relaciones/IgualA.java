package dominio.reglas.condiciones.relaciones;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "IGUAL_A")
public class IgualA extends Relacion {

	private double unValor;

	public IgualA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor == unValor;
	}
}