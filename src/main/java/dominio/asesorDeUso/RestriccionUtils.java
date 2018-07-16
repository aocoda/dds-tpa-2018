package dominio.asesorDeUso;

import java.util.List;
import java.util.function.ToDoubleFunction;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import dominio.dispositivos.Dispositivo;

public interface RestriccionUtils {

	public default LinearConstraint restriccionUsoMinimo(double [] coeficientes, Dispositivo unDispositivo) {
		
		return new LinearConstraint(coeficientes, Relationship.GEQ, unDispositivo.horasDeUsoMinimo());
	}
	
	public default LinearConstraint restriccionUsoMaximo(double [] coeficientes, Dispositivo unDispositivo) {
		
		return new LinearConstraint(coeficientes, Relationship.LEQ, unDispositivo.horasDeUsoMaximo());
	}
	
	public default LinearConstraint restriccionConsumoMaximo(double [] coeficientes) {
		
		return new LinearConstraint(coeficientes, Relationship.LEQ, 612);
	}
	
	public default double [] coeficientes(List<Dispositivo> dispositivos, ToDoubleFunction<Dispositivo> mapper) {
		
		return dispositivos.stream().mapToDouble(mapper).toArray();
	}
}