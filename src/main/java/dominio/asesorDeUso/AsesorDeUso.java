package dominio.asesorDeUso;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.Periodo;

public class AsesorDeUso implements RestriccionUtils {

	public Collection<Recomendacion> getHorasOptimas(List<Dispositivo> dispositivos) {
		
		return dispositivos
				.stream()
				.map(dispositivo -> new Recomendacion(dispositivo, valoresOptimos(dispositivos).get(dispositivos.indexOf(dispositivo))))
				.collect(Collectors.toList());
	}

	public Collection<Recomendacion> recomendacionesPara(List<Dispositivo> dispositivos, Periodo unPeriodo) {
		
		return getHorasOptimas(dispositivos)
				.stream()
				.map(recomendacion -> new Recomendacion(recomendacion.getDispositivoDeInteres(), recomendacion.horasDeUsoRestantesDe(unPeriodo)))
				.collect(Collectors.toList());
	}
	
	
	
	
	//AUXILIARES
	private List<Double> valoresOptimos(List<Dispositivo> dispositivos) {
		
		double [] valores = new SimplexSolver()
				.optimize(funcionEconomica(dispositivos), restricciones(dispositivos), GoalType.MAXIMIZE)
				.getPoint();
		
		return Arrays.stream(valores).boxed().collect(Collectors.toList());
	}
	
	private LinearConstraintSet restricciones(List<Dispositivo> dispositivos) {
		
		LinearConstraint restriccionConsumoTotal = restriccionConsumoMaximo(coeficientes(dispositivos, unDispositivo -> unDispositivo.getConsumoPorHora()));
		
		Stream<LinearConstraint> restriccionesDeUso = dispositivos
				.stream()
				.flatMap(dispositivo -> {
					
					double [] coeficientes = coeficientes(dispositivos, unDispositivo -> unDispositivo.equals(dispositivo) ? 1 : 0);
					
					return Stream.of(restriccionUsoMinimo(coeficientes, dispositivo), restriccionUsoMaximo(coeficientes, dispositivo));
				});
		
		Collection<LinearConstraint> restriccionesTotales = Stream
				.concat(Stream.of(restriccionConsumoTotal), restriccionesDeUso)
				.collect(Collectors.toList());
		
		return new LinearConstraintSet(restriccionesTotales);
	}
	
	private LinearObjectiveFunction funcionEconomica(List<Dispositivo> dispositivos) {
		
		return new LinearObjectiveFunction(coeficientes(dispositivos, d -> 1), 0);
	}
}