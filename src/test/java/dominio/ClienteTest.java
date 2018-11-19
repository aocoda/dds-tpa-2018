package dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;

import org.junit.Test;

public class ClienteTest {

	private Cliente cliente;
	private List<DispositivoEstandar> dispositivosEstandar = new ArrayList<>();
	private List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
	private DispositivoInteligente heladera = new DispositivoInteligente("Heladera", 100, 0, 0);
	private DispositivoInteligente televisor = new DispositivoInteligente("Televisor", 50, 0, 0);

	public Cliente construirClienteTest(Categoria categoria) {

		return new Cliente(null, null, 0, null, null, null, categoria, dispositivosEstandar, dispositivosInteligentes, null, null, null);
	}

	// ExisteDispositivoEncendido
	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_ExisteDispositivoEncendido_DebeDarFalso() {

		cliente = construirClienteTest(null);

		assertFalse(cliente.existeDispositivoEncendido());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnSoloElementoEncendido_ExisteDispositivoEncendido_DebeDarTrue() {

		heladera.encender();

		dispositivosInteligentes.add(heladera);

		cliente = construirClienteTest(null);

		assertTrue(cliente.existeDispositivoEncendido());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnElementoPrendidoYUnoApagado_ExisteDispositivoEncendido_DebeDarTrue() {

		heladera.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null);

		assertTrue(cliente.existeDispositivoEncendido());
	}

	// CantidadDeDispositivosEncendidos
	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneDosElementosApagados_CantidadDeDipositivosEncendidos_DebeDarCero() {

		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null);

		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesTieneUnElementoEncendidoYUnoApagado_CantidadDeDipositivosEncendidos_DebeDarUno() {

		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null);

		assertEquals(1, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_CantidadDeDipositivosEncendidos_DebeDarCero() {

		cliente = construirClienteTest(null);

		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	// CantidadDeDispositivosApagados
	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneDosElementosPrendidos_CantidadDipositivosApagados_DebeDarCero() {

		heladera.encender();
		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null);

		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaListaDeDispositvosInteligentesTieneUnElementoPrendidoYUnoApagado_CantidadDipositivosApagados_DebeDarUno() {

		televisor.encender();
		dispositivosInteligentes.add(heladera);
		dispositivosInteligentes.add(televisor);

		cliente = construirClienteTest(null);

		assertEquals(1, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaListaDeDispositivosInteligentesEstaVacia_CantidadDipositivosApagados_DebeDarCero() {

		cliente = construirClienteTest(null);

		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	// Recategorizar
	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYNoHayNingunaCategoriaQueLeCorrespondaLeQuedaLaCategoriaAnterior() {

		dispositivosEstandar.add(new DispositivoEstandar("Heladera", 400, 10, 0, 0));

		Categoria categoriaVieja = new Categoria(TipoCategoria.R1, 18.76, 0.644, 0, 150);

		List<Categoria> categorias = Stream.of(categoriaVieja).collect(Collectors.toList());

		cliente = construirClienteTest(categoriaVieja);

		Periodo periodoDeUnMes = new Periodo(LocalDateTime.of(2018, 1, 1, 0, 0), LocalDateTime.of(2018, 2, 1, 0, 0));
		
		cliente.recategorizar(categorias, periodoDeUnMes);

		assertEquals(categoriaVieja, cliente.getCategoria());
	}

	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYExisteCategoriaQueLeCorrespondaSeRecategorizaConExito() {

		dispositivosEstandar.add(new DispositivoEstandar("Heladera", 3, 4, 0, 0));

		Categoria categoriaVieja = new Categoria(TipoCategoria.R1, 18.76, 0.644, 0, 150);
		Categoria categoriaQueLeCorresponde = new Categoria(TipoCategoria.R3, 60.71, 0.681, 325, 400);

		List<Categoria> categorias = Stream.of(categoriaQueLeCorresponde).collect(Collectors.toList());

		cliente = construirClienteTest(categoriaVieja);

		Periodo periodoDeUnMes = new Periodo(LocalDateTime.of(2018, 1, 1, 0, 0), LocalDateTime.of(2018, 2, 1, 0, 0));
		
		cliente.recategorizar(categorias, periodoDeUnMes);

		assertEquals(categoriaQueLeCorresponde, cliente.getCategoria());
	}
	
	// Transformar
	@Test
	public void siSeTransformaUnDispositivoEstandarDebeCambiarseDeColeccion() {
		
		DispositivoEstandar dispositivoATransformar = new DispositivoEstandar("A", 0, 0, 0, 0);
		
		dispositivosEstandar.add(dispositivoATransformar);
		
		cliente = construirClienteTest(null);
		
		cliente.transformar(dispositivoATransformar);
		
		assertEquals(dispositivosEstandar.size(), 0);
		assertEquals(dispositivosInteligentes.size(), 1);
	}
}