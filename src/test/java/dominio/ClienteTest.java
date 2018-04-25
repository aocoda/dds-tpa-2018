package dominio;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Test;

import repositorios.RepositorioCategorias;

public class ClienteTest {

	private Cliente cliente;
	private Set<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	
	@After
	public void tearDown() {
		
		RepositorioCategorias.getInstancia().eliminarTodos();
	}
	
	public Cliente construirClienteTest(Categoria categoria, Set<Dispositivo> dispositivos) {
		
		return new Cliente(null , null, null, null, null, categoria, dispositivos);
	}	

	//ExisteDispositivoEncendido
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_ExisteDispositivoEncendido_DebeDarFalso() {
		
		cliente = construirClienteTest(null, dispositivos);			
		
		assertFalse(cliente.existeDispositivoEncendido());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosTieneUnSoloElementoEncendido_ExisteDispositivoEncendido_DebeDarTrue() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertTrue(cliente.existeDispositivoEncendido());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosTieneDosElementos_ExisteDispositivoEncendido_DebeDarTrue() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		dispositivos.add(new Dispositivo("Televisor", 300, false));
		
		cliente = construirClienteTest(null, dispositivos);		
		
		assertTrue(cliente.existeDispositivoEncendido());
	}
	
	//CantidadDeDispositivosEncendidos
	@Test
	public void cuandoLaCantidadDeDispositivosEncendidosEsCero_CantidadDeDipositivosEncendidos_DebeDarCero() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, false));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaCantidadDeDispositivosEncendidosEsUno_CantidadDeDipositivosEncendidos_DebeDarUno() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(1, cliente.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_CantidadDeDipositivosEncendidos_DebeDarCero() {
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}
	
	//CantidadDeDispositivosApagados
	@Test
	public void cuandoLaCantidadDeDispositivosApagadosEsCero_CantidadDipositivosApagados_DebeDarCero() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaCantidadDeDispositivosApagadosEsUno_CantidadDipositivosApagados_DebeDarUno() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(1, cliente.cantidadDispositivosApagados());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_CantidadDipositivosApagados_DebeDarCero() {
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosApagados());
	}
	
	//ConsumoMensual
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_ConsumoMensual_DebeDarCero() {
	
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(0, cliente.consumoMensual(), 0);
	}
	
	@Test
	public void cuandoUnDispositivoQueConsumeCuatrocientos_ConsumoMensual_DebeDarCuatrocientos() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		
		cliente = construirClienteTest(null, dispositivos);	
		
		assertEquals(400, cliente.consumoMensual(), 0);
	}
	
	//Recategorizar
	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYNoHayNingunaCategoríaQueLeCorrespondaLeQuedaLaCategoriaAnterior() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		
		Categoria categoriaVieja = new Categoria(SubtipoCategoria.R1, 18.76, 0.644, 0, 150);
		
		RepositorioCategorias.getInstancia().agregar(categoriaVieja);
		
		cliente = construirClienteTest(categoriaVieja, dispositivos);	
		
		cliente.recategorizar();
		
		assertEquals(categoriaVieja, cliente.getCategoria());
	}
	
	@Test
	public void cuandoUnClienteSeIntentaRecategorizarYExisteCategoríaQueLeCorrespondaSeRecategorizaConExito() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		
		Categoria categoriaVieja = new Categoria(SubtipoCategoria.R1, 18.76, 0.644, 0, 150);
		Categoria categoriaQueLeCorresponde = new Categoria(SubtipoCategoria.R3, 60.71, 0.681, 325, 400);
		
		RepositorioCategorias.getInstancia().agregar(categoriaQueLeCorresponde);
		
		cliente = construirClienteTest(categoriaVieja, dispositivos);	
		
		cliente.recategorizar();
		
		assertEquals(categoriaQueLeCorresponde, cliente.getCategoria());
	}
}