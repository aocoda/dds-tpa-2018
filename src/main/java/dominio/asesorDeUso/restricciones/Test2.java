package dominio.asesorDeUso.restricciones;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.optim.linear.Relationship;

import dominio.asesorDeUso.AsesorDeUso;
import dominio.asesorDeUso.restricciones.tiposRestriccion.ConsumoTotal;
import dominio.asesorDeUso.restricciones.tiposRestriccion.HorasDeUso;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.mocks.DispositivoMock;

public class Test2 {

	public static void main(String[] args) {
		
		//DISPOSITIVOS
		DispositivoInteligente d1 = new DispositivoMock("aire", 1.613);
				
		DispositivoInteligente d2 = new DispositivoMock("microondas", 0.64);
				
		List<DispositivoInteligente> dispositivos = Arrays.asList(d1, d2);
		
		//USO DE LOS DISPOSITIVOS
		Periodo periodo1 = Periodo.deLasUltimasNHoras(200);
		
		d1.addUso(periodo1);

		Periodo periodo2 = Periodo.deLasUltimasNHoras(10);
		
		d2.addUso(periodo2);
		
		
		
		
		// SIMPLEX
		Restriccion r = new Restriccion(new ConsumoTotal(), Relationship.LEQ, 300);

		Restriccion r1a = new Restriccion(new HorasDeUso(d1), Relationship.LEQ, 360);
		Restriccion r2a = new Restriccion(new HorasDeUso(d1), Relationship.GEQ, 90);
		
		Restriccion r1b = new Restriccion(new HorasDeUso(d2), Relationship.LEQ, 15);
		Restriccion r2b = new Restriccion(new HorasDeUso(d2), Relationship.GEQ, 3);

		Collection<Restriccion> restricciones = Arrays.asList(r1a, r2a, r1b, r2b, r);

		AsesorDeUso asesor = new AsesorDeUso(restricciones);
		
		
		
		
		// RESULTADO: Si el periodo fuera menor o mayor a un mes, no tendría sentido el resultado.
		Periodo periodo = Periodo.deLosUltimosNMeses(1);
		
		Map<DispositivoInteligente, Double> resultado = asesor.recomendacionesPara(dispositivos, periodo);

		System.out.println(resultado);
	}
}