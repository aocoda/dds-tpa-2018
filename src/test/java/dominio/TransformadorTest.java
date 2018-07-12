package dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.mocks.DispositivoMock;

public class TransformadorTest {

	private Cliente cliente1;
	private Cliente cliente2;
	Transformador transformador;
	private Set<DispositivoEstandar> DE1 = new HashSet<DispositivoEstandar>();
	private Set<DispositivoInteligente> DI1 = new HashSet<DispositivoInteligente>();
	private Set<DispositivoEstandar> DE2 = new HashSet<DispositivoEstandar>();
	private Set<DispositivoInteligente> DI2 = new HashSet<DispositivoInteligente>();
	private Set<Cliente> clientes = new HashSet<Cliente>();
	private DispositivoInteligente heladera = new DispositivoMock("Heladera", 100);
	private DispositivoInteligente televisor = new DispositivoMock("Televisor", 50);
	private LocalDateTime dia1 = LocalDateTime.of(2018, 4, 1, 0, 0);
	private LocalDateTime dia2 = LocalDateTime.of(2018, 4, 2, 0, 0);
	private LocalDateTime dia3 = LocalDateTime.of(2018, 4, 3, 0, 0);
	Periodo dia1a2 = new Periodo(dia1, dia2);
	Periodo dia2a3 = new Periodo(dia2, dia3);
	Periodo dia1a3 = new Periodo(dia1, dia3);

	public Cliente construirClienteTest(Set<DispositivoEstandar> dispositivosEstandar,
			Set<DispositivoInteligente> dispositivosInteligentes) {
		return new Cliente(null, null, 0, null, null, null, null, dispositivosEstandar, dispositivosInteligentes);
	}
	public Transformador construirTransformadorTest(Set<Cliente> clientes) {
		return new Transformador(clientes);
	}

	@Test
	public void transformadorCon2ClientesConDIEncendidosEnElPeriodo() {
		heladera.encender();
		televisor.encender();
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a2);
		DI1.add(heladera);
		DI2.add(televisor);
		cliente1 = construirClienteTest(DE1, DI1);
		cliente2 = construirClienteTest(DE2, DI2);
		clientes.add(cliente1);
		clientes.add(cliente2);
		transformador = construirTransformadorTest(clientes);

		assertEquals(3600, transformador.consumoDe(dia1a2), 0);
	}

	@Test
	public void transformadorCon2ClientesConDIEncendidosPeroSoloUnoEnElPeriodo() {
		heladera.encender();
		televisor.encender();
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a3);
		DI1.add(heladera);
		DI2.add(televisor);
		cliente1 = construirClienteTest(DE1, DI1);
		cliente2 = construirClienteTest(DE2, DI2);
		clientes.add(cliente1);
		clientes.add(cliente2);
		transformador = construirTransformadorTest(clientes);

		assertEquals(1200, transformador.consumoDe(dia2a3), 0);
	}

	@Test
	public void transformadorSinClientesEnUnPeriodo() {

		transformador = construirTransformadorTest(clientes);

		assertEquals(0, transformador.consumoDe(dia2a3), 0);
	}

	@Test
	public void transformadorConClientesPeroNingunoConsumiendoDentroDelPeriodo() {
		heladera.encender();
		televisor.encender();
		heladera.agregarUso(dia1a2);
		televisor.agregarUso(dia1a2);
		DI1.add(heladera);
		DI2.add(televisor);
		cliente1 = construirClienteTest(DE1, DI1);
		cliente2 = construirClienteTest(DE2, DI2);
		clientes.add(cliente1);
		clientes.add(cliente2);
		transformador = construirTransformadorTest(clientes);

		assertEquals(0, transformador.consumoDe(dia2a3), 0);
	}
}