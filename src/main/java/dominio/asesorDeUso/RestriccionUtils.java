package dominio.asesorDeUso;

import java.util.Collection;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public default Collection<LinearConstraint> restriccionesTotales(List<Dispositivo> dispositivos) {
		
		LinearConstraint restriccionConsumoTotal = restriccionConsumoMaximo(coeficientes(dispositivos, unDispositivo -> unDispositivo.getConsumoPorHora()));
		
		Stream<LinearConstraint> restriccionesDeUso = dispositivos
				.stream()
				.flatMap(dispositivo -> {
					
					double [] coeficientes = coeficientes(dispositivos, unDispositivo -> unDispositivo.equals(dispositivo) ? 1 : 0);
					
					return Stream.of(restriccionUsoMinimo(coeficientes, dispositivo), restriccionUsoMaximo(coeficientes, dispositivo));
				});
		
		return Stream.concat(Stream.of(restriccionConsumoTotal), restriccionesDeUso).collect(Collectors.toList());
	}
}