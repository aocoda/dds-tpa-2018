package dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.mocks.DispositivoMock;

public class TransformadorTest {

	private DispositivoInteligente heladera = new DispositivoMock("Heladera", 100);
	private DispositivoInteligente televisor = new DispositivoMock("Televisor", 50);

	private Set<DispositivoInteligente> DI1 = Collections.singleton(heladera);
	private Set<DispositivoInteligente> DI2 = Collections.singleton(televisor);
	
	private Cliente cliente1 = construirClienteTest(DI1);
	private Cliente cliente2 = construirClienteTest(DI2);;
	
	private Transformador transformador = new Transformador(null, Arrays.asList(cliente1, cliente2));
	
	private LocalDateTime dia1 = LocalDateTime.of(2018, 4, 1, 0, 0);
	private LocalDateTime dia2 = LocalDateTime.of(2018, 4, 2, 0, 0);
	private LocalDateTime dia3 = LocalDateTime.of(2018, 4, 3, 0, 0);
	
	private Periodo dia1a2 = new Periodo(dia1, dia2);
	private Periodo dia2a3 = new Periodo(dia2, dia3);
	private Periodo dia1a3 = new Periodo(dia1, dia3);

	public Cliente construirClienteTest(Set<DispositivoInteligente> dispositivosInteligentes) {
		
		return new Cliente(null, null, 0, null, null, null, null, new HashSet<>(), dispositivosInteligentes);
	}

	@Test
	public void transformadorCon2ClientesConDIEncendidosEnElPeriodo() {
		
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a2);

		assertEquals(3600, transformador.consumoDe(dia1a2), 0);
	}

	@Test
	public void transformadorCon2ClientesConDIEncendidosPeroSoloUnoEnElPeriodo() {
		
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a3);
		
		assertEquals(1200, transformador.consumoDe(dia2a3), 0);
	}

	@Test
	public void transformadorSinClientesEnUnPeriodo() {

		transformador = new Transformador(null, Collections.emptySet());

		assertEquals(0, transformador.consumoDe(dia2a3), 0);
	}

	@Test
	public void transformadorConClientesPeroNingunoConsumiendoDentroDelPeriodo() {
		
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a2);

		assertEquals(0, transformador.consumoDe(dia2a3), 0);
	}
}