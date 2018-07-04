package dominio.entrega2.iteracion2;

import java.util.Collection;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import dominio.dispositivos.DispositivoInteligente;

public class RestriccionConsumoMenor extends Restriccion {
	
	private Double valor;
	
	public RestriccionConsumoMenor(double valor) {

		this.valor = valor;
	}

	public double[] getCoeficientes(Collection<DispositivoInteligente> dispositivos) {
		
		return dispositivos
				.stream()
				.mapToDouble(d -> d.getConsumoPorHora())
				.toArray();
	}

	@Override
	public LinearConstraint toLinearConstraint(Collection<DispositivoInteligente> dispositivos) {
		
		return new LinearConstraint(getCoeficientes(dispositivos), Relationship.LEQ, valor);
	}
}