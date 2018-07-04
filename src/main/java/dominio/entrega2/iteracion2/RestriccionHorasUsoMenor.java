package dominio.entrega2.iteracion2;

import java.util.Collection;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import dominio.dispositivos.DispositivoInteligente;

public class RestriccionHorasUsoMenor extends Restriccion {

	private DispositivoInteligente dispositivo;
	private double valor;
	
	public RestriccionHorasUsoMenor(DispositivoInteligente dispositivo, double valor) {
		
		this.dispositivo = dispositivo;
		this.valor = valor;
	}

	public double[] getCoeficientes(DispositivoInteligente unDispositivo, Collection<DispositivoInteligente> dispositivos) {
		
		return dispositivos
				.stream()
				.mapToDouble(dispositivo -> dispositivo.equals(unDispositivo) ? 1 : 0)
				.toArray();
	}
	
	@Override
	public LinearConstraint toLinearConstraint(Collection<DispositivoInteligente> dispositivos) {
		
		return new LinearConstraint(getCoeficientes(dispositivo, dispositivos), Relationship.LEQ, valor);
	}
}