package dominio.entrega2.iteracion2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;

public class AsesorDeUso {

	private Collection<Restriccion> restricciones;
	
	public AsesorDeUso(Collection<Restriccion> restricciones) {
		
		this.restricciones = restricciones;
	}

	public Map<DispositivoInteligente, Double> getHorasOptimas(List<DispositivoInteligente> dispositivos) {
		
		return dispositivos
				.stream()
				.collect(Collectors.toMap(Function.identity(), d -> valoresOptimos(dispositivos).get(dispositivos.indexOf(d))));
	}

	public Map<DispositivoInteligente, Double> recomendacionesPara(List<DispositivoInteligente> dispositivos, Periodo unPeriodo) {
		
		return getHorasOptimas(dispositivos)
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Entry::getKey, e -> e.getValue() - e.getKey().horasDeUso(unPeriodo)));
	}
	
	public boolean superaHorasOptimas(DispositivoInteligente dispositivo, List<DispositivoInteligente> dispositivos, Periodo unPeriodo) {
		
		return recomendacionesPara(dispositivos, unPeriodo).get(dispositivo) < 0;
	}
	
	
	
	
	//AUXILIARES
	private List<Double> valoresOptimos(List<DispositivoInteligente> dispositivos) {
		
		double [] valores = new SimplexSolver()
				.optimize(funcionEconomica(dispositivos), getRestricciones(restricciones, dispositivos), GoalType.MAXIMIZE)
				.getPoint();
		
		return Arrays.stream(valores).boxed().collect(Collectors.toList());
	}
	
	private LinearConstraintSet getRestricciones(Collection<Restriccion> restricciones, List<DispositivoInteligente> dispositivos) {
		
		Collection<LinearConstraint> linearConstraints = restricciones
				.stream()
				.map(restriccion -> restriccion.toLinearConstraint(dispositivos))
				.collect(Collectors.toList());
		
		return new LinearConstraintSet(linearConstraints);
	}
	
	private LinearObjectiveFunction funcionEconomica(List<DispositivoInteligente> dispositivos) {
		
		double [] coeficientes = dispositivos.stream().mapToDouble(d -> 1).toArray();
		
		return new LinearObjectiveFunction(coeficientes, 0);
	}
}