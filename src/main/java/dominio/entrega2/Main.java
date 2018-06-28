package dominio.entrega2;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.assertj.core.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		//PASO 1: RESTRICCIONES
		/*
		Aca hay dos tipos:
			a) consumo mensaul maximo de un hogar (612)
			b) van los maximos y minimos de un dispositivo (quien conoce estos maximos y minimos??)
		 */
		LinearConstraint unaRestriccion = new LinearConstraint(coefficients, relationship, value);
		
		LinearObjectiveFunction funcionEconomica = new LinearObjectiveFunction(coefficients, constantTerm)
				
		double [] resultado = new SimplexSolver().optimize(funcionEconomica, new LinearConstraintSet(unaRestriccion)).getPoint();
	}
}
