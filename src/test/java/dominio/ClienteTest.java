package dominio;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ClienteTest {
	//Comentario para ver si se configuro bien la consola
	private Cliente cliente;
	private Set<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	
	public Cliente construirClienteTest(Set<Dispositivo> dispositivos) {
		
		return new Cliente(null , null, null, null, null, dispositivos);
	}	

	//ExisteDispositivoEncendido
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_ExisteDispositivoEncendido_DebeDarFalso() {
		
		cliente = construirClienteTest(dispositivos);			
		assertFalse(cliente.existeDispositivoEncendido());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosTieneUnSoloElementoEncendido_ExisteDispositivoEncendido_DebeDarTrue() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		cliente = construirClienteTest(dispositivos);	
		assertTrue(cliente.existeDispositivoEncendido());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosTieneDosElementos_ExisteDispositivoEncendido_DebeDarTrue() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		dispositivos.add(new Dispositivo("Televisor", 300, false));
		cliente = construirClienteTest(dispositivos);		
		
		assertTrue(cliente.existeDispositivoEncendido());
	}
	
	//CantidadDeDispositivosEncendidos
	@Test
	public void cuandoLaCantidadDeDispositivosEncendidosEsCero_CantidadDeDipositivosEncendidos_DebeDarCero() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, false));
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}

	@Test
	public void cuandoLaCantidadDeDispositivosEncendidosEsUno_CantidadDeDipositivosEncendidos_DebeDarUno() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(1, cliente.cantidadDispositivosEncendidos());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_CantidadDeDipositivosEncendidos_DebeDarCero() {
		
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosEncendidos());
	}
	
	//CantidadDeDispositivosApagados
	@Test
	public void cuandoLaCantidadDeDispositivosApagadosEsCero_CantidadDipositivosApagados_DebeDarCero() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosApagados());
	}

	@Test
	public void cuandoLaCantidadDeDispositivosApagadosEsUno_CantidadDipositivosApagados_DebeDarUno() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, false));
		dispositivos.add(new Dispositivo("Televisor", 300, true));
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(1, cliente.cantidadDispositivosApagados());
	}
	
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_CantidadDipositivosApagados_DebeDarCero() {
		
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(0, cliente.cantidadDispositivosApagados());
	}
	
	//ConsumoMensual
	@Test
	public void cuandoLaListaDeDispositivosEstaVacia_ConsumoMensual_DebeDarCero() {
	
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(0, cliente.consumoMensual(), 0);
	}
	
	@Test
	public void cuandoUnDispositivoQueConsumeCuatrocientos_ConsumoMensual_DebeDarCuatrocientos() {
		
		dispositivos.add(new Dispositivo("Heladera", 400, true));
		cliente = construirClienteTest(dispositivos);	
		
		assertEquals(400, cliente.consumoMensual(), 0);
	}
}