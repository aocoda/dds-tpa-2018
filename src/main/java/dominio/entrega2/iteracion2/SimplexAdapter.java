package dominio.entrega2.iteracion2;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivos.DispositivoInteligente;
import io.vavr.Tuple2;
import io.vavr.collection.List;

public class SimplexAdapter {

	private Collection<Restriccion> restricciones;
	
	public SimplexAdapter(Collection<Restriccion> restricciones) {
		
		this.restricciones = restricciones;
	}

	public Map<DispositivoInteligente, Double> getHorasOptimasDeUso(Collection<DispositivoInteligente> dispositivos) {
		
		return List
				.ofAll(dispositivos)
				.zip(valoresOptimos(dispositivos))
				.collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));
	}

	
	
	
	
	
	
	
	//AUXILIARES
	public Collection<Double> valoresOptimos(Collection<DispositivoInteligente> dispositivos) {
		
		return List
				.ofAll(new SimplexSolver()
						.optimize(funcionEconomica(dispositivos), getRestricciones(restricciones, dispositivos), GoalType.MAXIMIZE)
						.getPoint())
				.asJava();
	}
	
	public LinearConstraintSet getRestricciones(Collection<Restriccion> restricciones, Collection<DispositivoInteligente> dispositivos) {
		
		return new LinearConstraintSet(restricciones.stream().map(restriccion -> restriccion.toLinearConstraint(dispositivos)).collect(Collectors.toList()));
	}
	
	public LinearObjectiveFunction funcionEconomica(Collection<DispositivoInteligente> dispositivos) {
		
		return new LinearObjectiveFunction(dispositivos.stream().mapToDouble(d -> 1).toArray(), 0);
	}
}