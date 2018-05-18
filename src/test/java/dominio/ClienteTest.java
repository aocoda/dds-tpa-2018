package dominio;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.mocks.DispositivoMock;

import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	private Set<DispositivoEstandar> dispositivosEstandar = new HashSet<DispositivoEstandar>();
	private Set<DispositivoInteligente> dispositivosInteligentes = new HashSet<DispositivoInteligente>();
	private DispositivoInteligente heladera = new DispositivoMock("Heladera", 100);
	private DispositivoInteligente televisor = new DispositivoMock("Televisor", 50);

	public Cliente construirClienteTest(Categoria categoria, Set<DispositivoEstandar> dispositivosEstandar,
			Set<DispositivoInteligente> dispositivosInteligentes) {

		return new Cliente(null, null, 0, null, null, null, categoria, dispositivosEstandar, dispositivosInteligentes);
	}

	// ExisteDispositivoEncendido
	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_ExisteDispositivoEncendido_DebeDarFalso() {

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertFalse(cliente.existeDispositivoEncendido());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnSoloElementoEncendido_ExisteDispositivoEncendido_DebeDarTrue() {

		heladera.encender();

		dispositivosInteligentes.add(heladera);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertTrue(cliente.existeDispositivoEncendido());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnElementoPrendidoYUnoApagado_ExisteDispositivoEncendido_DebeDarTrue() {

		heladera.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertTrue(cliente.existeDispositivoEncendido());
	}

	// CantidadDeDispositivosEncendidos
	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneDosElementosApagados_CantidadDeDipositivosEncendidos_DebeDarCero() {

		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnElementoEncendidoYUnoApagado_CantidadDeDipositivosEncendidos_DebeDarUno() {

		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(1, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_CantidadDeDipositivosEncendidos_DebeDarCero() {

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	// CantidadDeDispositivosApagados
	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneDosElementosPrendidos_CantidadDipositivosApagados_DebeDarCero() {

		heladera.encender();
		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneUnElementoPrendidoYUnoApagado_CantidadDipositivosApagados_DebeDarUno() {

		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(1, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_CantidadDipositivosApagados_DebeDarCero() {

		cliente = construirClienteTest(null, null, dispositivosInteligentes);

		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	// Recategorizar
	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYNoHayNingunaCategoriaQueLeCorrespondaLeQuedaLaCategoriaAnterior() {

		dispositivosEstandar.add(new DispositivoEstandar("Heladera", 400, 10));

		Categoria categoriaVieja = new Categoria(SubtipoCategoria.R1, 18.76, 0.644, 0, 150);

		Set<Categoria> categorias = Stream.of(categoriaVieja).collect(Collectors.toSet());

		cliente = construirClienteTest(categoriaVieja, dispositivosEstandar, null);

		cliente.recategorizar(categorias);

		assertEquals(categoriaVieja, cliente.getCategoria());
	}

	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYExisteCategoriaQueLeCorrespondaSeRecategorizaConExito() {

		dispositivosEstandar.add(new DispositivoEstandar("Heladera", 3, 4));

		Categoria categoriaVieja = new Categoria(SubtipoCategoria.R1, 18.76, 0.644, 0, 150);
		Categoria categoriaQueLeCorresponde = new Categoria(SubtipoCategoria.R3, 60.71, 0.681, 325, 400);

		Set<Categoria> categorias = Stream.of(categoriaQueLeCorresponde).collect(Collectors.toSet());

		cliente = construirClienteTest(categoriaVieja, dispositivosEstandar, null);

		cliente.recategorizar(categorias);

		assertEquals(categoriaQueLeCorresponde, cliente.getCategoria());
	}

	// ESTO HAY QUE VER SI VA, HAY QUE PROBAR TANTO INTELIGENTES COMO ESTANDARES Y
	// EN DISTINTOS PERIODOS.
	// QUE ES MENSUAL? EL MES PUEDE TENER 28, 29, 30, o 31 dias, lo que daria
	// distintos consumos.
	// ConsumoMensual
	@Test
	public void cuandoLaListaDeDispositivosEstandarEstaVacia_ConsumoMensual_DebeDarCero() {

		cliente = construirClienteTest(null, dispositivosEstandar, null);

		assertEquals(0, cliente.consumoDelMesCorriente(), 0);
	}

	@Test
	public void cuandoLaListaDeDispositivosEstandarTieneUnElementoQueConsumeCuatrocientos_ConsumoMensual_DebeDarCuatrocientos() {

		dispositivosEstandar.add(new DispositivoEstandar("Heladera", 40, 10));

		cliente = construirClienteTest(null, dispositivosEstandar, null);

		assertEquals(400, cliente.consumoDelMesCorriente(), 0);
	}
}