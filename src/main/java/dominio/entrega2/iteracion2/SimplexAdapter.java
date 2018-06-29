package dominio.entrega2.iteracion2;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivos.DispositivoInteligente;
import io.vavr.Tuple2;
import io.vavr.collection.List;

public class SimplexAdapter {

	
	public Map<DispositivoInteligente, Double> getHorasOptimasDeUso(Collection<DispositivoInteligente> dispositivos) {
		
		return List
				.ofAll(dispositivos)
				.zip(valoresOptimos(dispositivos))
				.collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));
	}

	public LinearObjectiveFunction funcionEconomica(Collection<DispositivoInteligente> dispositivos) {
		
		return new LinearObjectiveFunction(coeficientesFuncionEconomica(dispositivos), 0);
	}
	
	public double [] coeficientesFuncionEconomica(Collection<DispositivoInteligente> dispositivos) {
	
		return dispositivos.stream().mapToDouble(d -> 1).toArray();
	}
	
	public Collection<Double> valoresOptimos(Collection<DispositivoInteligente> dispositivos) {
		
		return List
				.ofAll(new SimplexSolver()
						.optimize(funcionEconomica(dispositivos), null, GoalType.MAXIMIZE)
						.getPoint())
				.asJava();
	}
}