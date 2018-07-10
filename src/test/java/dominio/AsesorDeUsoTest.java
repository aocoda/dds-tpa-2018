package dominio;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.optim.linear.Relationship;
import org.junit.Test;

import dominio.asesorDeUso.AsesorDeUso;
import dominio.asesorDeUso.restricciones.Restriccion;
import dominio.asesorDeUso.restricciones.tiposRestriccion.ConsumoTotal;
import dominio.asesorDeUso.restricciones.tiposRestriccion.HorasDeUso;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.mocks.DispositivoMock;
import dominio.reglas.Actuador;
import dominio.reglas.Regla;
import dominio.reglas.condiciones.CondicionSimplex;


public class AsesorDeUsoTest {

	@Test
	public void getHorasOptimasParaDosDispositivos() {
		
		//DISPOSITIVOS
		DispositivoInteligente d1 = new DispositivoMock("aire", 1.613);
		
		DispositivoInteligente d2 = new DispositivoMock("microondas", 0.64);
		
		List<DispositivoInteligente> dispositivos = Arrays.asList(d1, d2);
		
		
		
		//SIMPLEX
		Restriccion r = new Restriccion(new ConsumoTotal(), Relationship.LEQ, 300);

		Restriccion r1a = new Restriccion(new HorasDeUso(d1), Relationship.LEQ, 360);
		Restriccion r2a = new Restriccion(new HorasDeUso(d1), Relationship.GEQ, 90);
		
		Restriccion r1b = new Restriccion(new HorasDeUso(d2), Relationship.LEQ, 15);
		Restriccion r2b = new Restriccion(new HorasDeUso(d2), Relationship.GEQ, 3);
		
		Collection<Restriccion> restricciones = Arrays.asList(r1a, r2a, r1b, r2b, r);
		
		AsesorDeUso asesor = new AsesorDeUso(restricciones);
		
		
		
		//RESULTADO
		Map<DispositivoInteligente, Double> resultado = asesor.getHorasOptimas(dispositivos);
		
		
		
		//ASERSIONES
		assertEquals(180, resultado.get(d1), 0.5);
		assertEquals(15, resultado.get(d2), 0.5);
	}
	
	@Test
	public void recomendacionesParaDosDispositivosConUsos() {
		
		//DISPOSITIVOS
		DispositivoInteligente d1 = new DispositivoMock("aire", 1.613);

		DispositivoInteligente d2 = new DispositivoMock("microondas", 0.64);

		List<DispositivoInteligente> dispositivos = Arrays.asList(d1, d2);

		
		
		//USO DE LOS DISPOSITIVOS
		Periodo periodo1 = Periodo.deLasUltimasNHoras(200);

		d1.addUso(periodo1);

		Periodo periodo2 = Periodo.deLasUltimasNHoras(10);

		d2.addUso(periodo2);

		
		
		//SIMPLEX
		Restriccion r = new Restriccion(new ConsumoTotal(), Relationship.LEQ, 300);

		Restriccion r1a = new Restriccion(new HorasDeUso(d1), Relationship.LEQ, 360);
		Restriccion r2a = new Restriccion(new HorasDeUso(d1), Relationship.GEQ, 90);

		Restriccion r1b = new Restriccion(new HorasDeUso(d2), Relationship.LEQ, 15);
		Restriccion r2b = new Restriccion(new HorasDeUso(d2), Relationship.GEQ, 3);

		Collection<Restriccion> restricciones = Arrays.asList(r1a, r2a, r1b, r2b, r);

		AsesorDeUso asesor = new AsesorDeUso(restricciones);

		
		
		//RESULTADO
		Periodo periodo = Periodo.deLosUltimosNMeses(1);

		Map<DispositivoInteligente, Double> resultado = asesor.recomendacionesPara(dispositivos, periodo);
		
		
		
		//ASERSIONES
		assertEquals(-20, resultado.get(d1), 0.5);
		assertEquals(5, resultado.get(d2), 0.5);
	}
	
	@Test
	public void condicionSimplex() {
		
		// DISPOSITIVOS

		// UNO: Si este excede el consumo recomendado se debe ejecutar la regla
		DispositivoMock dispositivo = new DispositivoMock("dispositivoTest", 10);
		// TODOS: Son todos los del cliente, solo los necesita el asesor.
		List<DispositivoInteligente> todos = Arrays.asList(dispositivo);

		// ASESOR

		// RESTRICCIONES: Son sobre el DISPOSITIVO o sobre TODOS
		Restriccion restriccion1 = new Restriccion(new HorasDeUso(dispositivo), Relationship.LEQ, 100);
		Restriccion restriccion2 = new Restriccion(new HorasDeUso(dispositivo), Relationship.GEQ, 0);
		Restriccion restriccion3 = new Restriccion(new ConsumoTotal(), Relationship.LEQ, 50);
		// ASESOR: Tiene las RESTRICCIONES
		AsesorDeUso asesor = new AsesorDeUso(Arrays.asList(restriccion1, restriccion2, restriccion3));

		// PERIODO: Es de 1 mes (se supone)
		Periodo periodo = Periodo.deLosUltimosNMeses(1);

		// REGLA

		// CONDICION: Necesita el ASESOR, el DISPOSITIVO, TODOS y PERIODO
		CondicionSimplex condicion = new CondicionSimplex(asesor, dispositivo, todos, periodo);

		// ACTUADOR
		Actuador apagador = d -> d.apagar();

		// REGLA: Los Dispositivos pueden ser TODOS o un SUBCONJUNTO de 1 o mas
		Regla regla = new Regla(condicion, apagador, Arrays.asList(dispositivo));
	}
}