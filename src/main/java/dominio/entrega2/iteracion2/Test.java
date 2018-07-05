package dominio.entrega2.iteracion2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dominio.dispositivos.DispositivoInteligente;
import dominio.mocks.DispositivoMock;

public class Test {

	public static void main(String[] args) {
		
		//DISPOSITIVOS
		DispositivoInteligente d1 = new DispositivoMock("aire", 1.613);
		
		DispositivoInteligente d2 = new DispositivoMock("microondas", 0.64);
		
		List<DispositivoInteligente> dispositivos = Arrays.asList(d1, d2);
		
		
		
		//SIMPLEX (MUCHISIMA LOGICA REPETIDA ENTRE RESTRICCIONES ESPECIALMENTE ENTRE HORASMAYOR Y HORASMENOR)
		Restriccion r = new RestriccionConsumoMenor(300);

		Restriccion r1a = new RestriccionHorasUsoMenor(d1, 360);
		Restriccion r2a = new RestriccionHorasUsoMayor(d1, 90);
		
		Restriccion r1b = new RestriccionHorasUsoMenor(d2, 15);
		Restriccion r2b = new RestriccionHorasUsoMayor(d2, 3);
		
		Collection<Restriccion> restricciones = Arrays.asList(r1a, r2a, r1b, r2b, r);
		
		AsesorDeUso asesor = new AsesorDeUso(restricciones);
		
		
		
		//RESULTADO
		Map<DispositivoInteligente, Double> resultado = asesor.getHorasOptimas(dispositivos);
		
		System.out.println(resultado);
	}
}