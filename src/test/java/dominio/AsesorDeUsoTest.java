package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

		d1.agregarUso(periodo1);

		Periodo periodo2 = Periodo.deLasUltimasNHoras(10);

		d2.agregarUso(periodo2);

		
		
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
		
		//1)
		//Dispositivo el cual se quiere saber si excedio el consumo, que fue usado 100 horas
		DispositivoMock dispositivo1 = new DispositivoMock("dispositivoTest", 10);
		dispositivo1.agregarUso(Periodo.deLasUltimasNHoras(100));
		
		DispositivoInteligente dispositivo2 = new DispositivoMock("dispositivoTest2", 20);
		
		//Total de dispositivos (el asesor los necesita)
		List<DispositivoInteligente> dispositivos = Arrays.asList(dispositivo1, dispositivo2);
		
		
		
		//2) Asesor y restricciones
		//El dispositivo1 se deberia usar 50 horas
		Restriccion restriccion1 = new Restriccion(new HorasDeUso(dispositivo1), Relationship.LEQ, 50);
		//El dispositivo2 se deberia usar 0 horas
		Restriccion restriccion2 = new Restriccion(new HorasDeUso(dispositivo2), Relationship.EQ, 0);
		
		AsesorDeUso asesor = new AsesorDeUso(Arrays.asList(restriccion1, restriccion2));
		
		
		
		//3) Periodo de 1 mes de duracion
		Periodo periodo = Periodo.deLosUltimosNMeses(1);
		
		
		
		//4) Condicion
		CondicionSimplex condicion = new CondicionSimplex(asesor, dispositivo1, dispositivos, periodo);
		
		
		
		/*
		La recomendacion del asesor va a ser usarlo -50 horas (el óptimo era 50 y se usó 100).
		Por lo tanto la restriccion se excedió y la condicion se cumple
		*/
		assertEquals(-50, asesor.recomendacionesPara(dispositivos, periodo).get(dispositivo1), 0.5);
		assertTrue(condicion.seCumple());
	}
}