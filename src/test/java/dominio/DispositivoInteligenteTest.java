package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.DispositivoInteligente;

public class DispositivoInteligenteTest {

	private DispositivoInteligente dispositivoTest = new DispositivoInteligente("dispositivoTest", 100);

	@Test
	public void SiUnDispositivoEstaApagado_ySeLeDieraLaOrdenDeApagarse_noDeberaHacerNadaYPermancerApagado() {
		
		dispositivoTest.apagar();
		
		assertTrue(dispositivoTest.estaApagado());
	}
	
	@Test
	public void SiUnDispositivoEstaEncendido_ySeLeDieraLaOrdenDeEncenderse_noDeberaHacerNadaYPermancerEncendido() {
		
		dispositivoTest.encender();
		
		assertTrue(dispositivoTest.estaEncendido());
	}

	@Test
	public void darleLaOrdenDeApagarse_DebeApagarElEquipo() {
		
		dispositivoTest.apagar();
		
		assertTrue(dispositivoTest.estaApagado());
	}
	
	@Test
	public void darleLaOrdenDeEncender_DebeEncenderElEquipo() {
		
		dispositivoTest.encender();
		
		assertTrue(dispositivoTest.estaEncendido());
	}
	
	@Test
	public void unEquipoEnAhorroNoDebeEstarApagadoNiPrendido() {
		
		dispositivoTest.modoAhorro();
		
		assertTrue(!dispositivoTest.estaApagado());
		assertTrue(!dispositivoTest.estaEncendido());
	}
	
	@Test
	public void unEquipoEnAhorro_DebeEncenderseSiSeLeDaLaOrdenDeEncenderse() {
		
		dispositivoTest.encender();
		
		assertTrue(dispositivoTest.estaEncendido());
	}
}