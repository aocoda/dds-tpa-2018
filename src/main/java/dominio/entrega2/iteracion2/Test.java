package dominio.entrega2.iteracion2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.optim.linear.Relationship;

import dominio.dispositivos.DispositivoInteligente;
import dominio.mocks.DispositivoMock;

public class Test {

	public static void main(String[] args) {
		
		//DISPOSITIVOS
		DispositivoInteligente d1 = new DispositivoMock("aire", 1.613);
		
		DispositivoInteligente d2 = new DispositivoMock("microondas", 0.64);
		
		List<DispositivoInteligente> dispositivos = Arrays.asList(d1, d2);
		
		
		
		//SIMPLEX (MUCHISIMA LOGICA REPETIDA ENTRE RESTRICCIONES ESPECIALMENTE ENTRE HORASMAYOR Y HORASMENOR)
		Restriccion r = new Restriccion(new ConsumoTotal(), Relationship.LEQ, 300);

		Restriccion r1a = new Restriccion(new HorasDeUso(d1), Relationship.LEQ, 360);
		Restriccion r2a = new Restriccion(new HorasDeUso(d1), Relationship.GEQ, 90);
		
		Restriccion r1b = new Restriccion(new HorasDeUso(d2), Relationship.LEQ, 15);
		Restriccion r2b = new Restriccion(new HorasDeUso(d2), Relationship.GEQ, 3);
		
		Collection<Restriccion> restricciones = Arrays.asList(r1a, r2a, r1b, r2b, r);
		
		AsesorDeUso asesor = new AsesorDeUso(restricciones);
		
		
		
		//RESULTADO
		Map<DispositivoInteligente, Double> resultado = asesor.getHorasOptimas(dispositivos);
		
		System.out.println(resultado);
	}
}