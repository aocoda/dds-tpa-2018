package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.*;
import dominio.dispositivos.estados.*;

public class DispositivoInteligenteTest {

	private DispositivoInteligente heladera = new DispositivoInteligente("Heladera", 100, new Apagado());
	private DispositivoInteligente televisor = new DispositivoInteligente("Televisor", 100, new Encendido());
	private DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire Acondicionado", 100, new Ahorro());

	@Test
	public void SiUnDispositivoEstaApagado_ySeLeDieraLaOrdenDeApagarse_noDeberaHacerNadaYPermancerApagado(){
		heladera.apagar();
		assertTrue(heladera.estaApagado());
	}
	
	@Test
	public void SiUnDispositivoEstaEncendido_ySeLeDieraLaOrdenDeEncenderse_noDeberaHacerNadaYPermancerEncendido(){
		televisor.encender();
		assertTrue(televisor.estaEncendido());
	}

	@Test
	public void darleLaOrdenDeApagarse_DebeApagarElEquipo() {
		televisor.apagar();
		assertTrue(televisor.estaApagado());
	}
	
	@Test
	public void darleLaOrdenDeEncender_DebeEncenderElEquipo() {
		heladera.encender();
		assertTrue(heladera.estaEncendido());
	}
	
	@Test
	public void unEquipoEnAhorroNoDebeEstarApagadoNiPrendido() {
		aireAcondicionado.modoAhorro();
		assertTrue(!(aireAcondicionado.estaApagado()));
		assertTrue(!(aireAcondicionado.estaEncendido()));
	}
	
	@Test
	public void unEquipoEnAhorro_DebeEncenderseSiSeLeDaLaOrdenDeEncenderse() {
		aireAcondicionado.encender();
		assertTrue(aireAcondicionado.estaEncendido());
	}
}
