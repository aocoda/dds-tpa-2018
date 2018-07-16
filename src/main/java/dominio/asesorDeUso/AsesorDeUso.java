package dominio.asesorDeUso;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.Periodo;

public class AsesorDeUso {

	public Map<Dispositivo, Double> getHorasOptimas(List<Dispositivo> dispositivos) {
		
		return dispositivos
				.stream()
				.collect(Collectors.toMap(Function.identity(), d -> valoresOptimos(dispositivos).get(dispositivos.indexOf(d))));
	}

	public Map<Dispositivo, Double> recomendacionesPara(List<Dispositivo> dispositivos, Periodo unPeriodo) {
		
		return getHorasOptimas(dispositivos)
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue() - e.getKey().horasDeUso(unPeriodo)));
	}
	
	
	
	
	//AUXILIARES
	private List<Double> valoresOptimos(List<Dispositivo> dispositivos) {
		
		double [] valores = new SimplexSolver()
				.optimize(funcionEconomica(dispositivos), restricciones(dispositivos), GoalType.MAXIMIZE)
				.getPoint();
		
		return Arrays.stream(valores).boxed().collect(Collectors.toList());
	}
	
	private LinearConstraintSet restricciones(List<Dispositivo> dispositivos) {
		
		ToDoubleFunction<Dispositivo> mapper1 = unDispositivo -> unDispositivo.getConsumoPorHora();
		
		LinearConstraint restriccionConsumoTotal = new LinearConstraint(dispositivos.stream().mapToDouble(mapper1).toArray(), Relationship.LEQ, 612);
		
		Stream<LinearConstraint> restriccionesDeUso = dispositivos
				.stream()
				.flatMap(dispositivo -> {
					
					ToDoubleFunction<Dispositivo> mapper2 = unDispositivo -> unDispositivo.equals(dispositivo) ? 1 : 0;
					
					double [] coeficientes = dispositivos.stream().mapToDouble(mapper2).toArray();
					
					LinearConstraint restriccionUsoMinimo = new LinearConstraint(coeficientes, Relationship.GEQ, dispositivo.horasDeUsoMinimo());
					
					LinearConstraint restriccionUsoMaximo = new LinearConstraint(coeficientes, Relationship.LEQ, dispositivo.horasDeUsoMaximo());
					
					return Stream.of(restriccionUsoMinimo, restriccionUsoMaximo);
				});
		
		Collection<LinearConstraint> restricciones = Stream
				.concat(restriccionesDeUso, Stream.of(restriccionConsumoTotal))
				.collect(Collectors.toList());
		
		return new LinearConstraintSet(restricciones);
	}
	
	private LinearObjectiveFunction funcionEconomica(List<Dispositivo> dispositivos) {
		
		double [] coeficientes = dispositivos.stream().mapToDouble(d -> 1).toArray();
		
		return new LinearObjectiveFunction(coeficientes, 0);
	}
}