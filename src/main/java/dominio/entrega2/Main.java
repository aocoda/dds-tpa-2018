package dominio.entrega2;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class Main {

	public static void main(String[] args) {
		
		//SUPONEMOS UN TOTAL DE 2 VARIABLES: { X1, X2 } 
		//QUE REPRESENTAN LA CANTIDAD DE HORAS DE USO UN DISPOSITIVO (LO QUE NOSOTROS LLAMAMOS TOPE)

		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//PASO 1: RESTRICCIONES
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		Aca hay dos tipos:
			a) consumo mensual maximo de un hogar (612) (sum(coef x hs) de todos los disp)
			b) van los maximos y minimos de un dispositivo (EN HORAS) (quien conoce estos maximos y minimos??)
			c) restricciones de no negatividad (no tiene sentido, ya se contempla en el tipo b)
		 */
		
		//Restriccion tipo a)
		/*los coeficientes corresponden al "consumo por hora de cada dispositivo"
		por ejemplo X1 = AireAcondicionado, consumoPorHora = 1.613
					X2 = Microondas, consumoPorHora = 0.64
		*/
		LinearConstraint consumoMensual = new LinearConstraint(new double [] {1.613, 0.64}, Relationship.LEQ, 300);
		
		//Restriccion tipo b)
		
		//Restricciones para X1 = AireAcondicionado
		//Estas dos restricciones representan: 90 <= 1*X1 + 0*X2 =< 360
		LinearConstraint aireMenor = new LinearConstraint(new double [] {1, 0}, Relationship.LEQ, 360);
		LinearConstraint aireMayor = new LinearConstraint(new double [] {1, 0}, Relationship.GEQ, 90);
		
		//Restricciones para X2 = Microondas
		//Estas dos restricciones representan: 3 <= 0*X1 + 1*X2 =< 15
		LinearConstraint microondasMenor = new LinearConstraint(new double [] {0, 1}, Relationship.LEQ, 15);
		LinearConstraint microondasMayor = new LinearConstraint(new double [] {0, 1}, Relationship.GEQ, 3);
		
		LinearConstraintSet restricciones = new LinearConstraintSet(aireMenor, aireMayor, microondasMenor, microondasMayor, consumoMensual);
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//PASO 2: FUNCION ECONOMICA
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		 el {1, 1} significa que tanto el coeficiente de X1, como el de X2 seran uno.
		 el 0 corresponde a un termino independiente D = 0
		 
		 Esto se traduce en una funcion: 1*X1 + 1*X2 + D=0 = Z 
		*/
		LinearObjectiveFunction funcionEconomica = new LinearObjectiveFunction(new double [] {1, 1}, 0);
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//PASO 3: SIMPLEX Y RESULTADO
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		double [] resultado = new SimplexSolver()
				.optimize(funcionEconomica, restricciones, GoalType.MAXIMIZE)
				.getPoint();
		
		
		for (double d : resultado) {
			
			System.out.println(d);
		}
		
		
		//ACA UN EJEMPLO: http://mail-archives.apache.org/mod_mbox/commons-user/201307.mbox/%3CCAJSjvws+9uC=jMP_A_Mbc4szbJL2VXAwp=Q2A+zZ=51mLeRw6g@mail.gmail.com%3E
	}
}
