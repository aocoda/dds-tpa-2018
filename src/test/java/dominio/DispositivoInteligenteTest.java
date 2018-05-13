package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.DispositivoInteligente;

public class DispositivoInteligenteTest {

	private DispositivoInteligente dispositivo = new DispositivoInteligente();

	@Test
	public void unEquipoEncendidoTieneQueEstarEncendido() {
		dispositivo.encender();
		assertTrue(dispositivo.estaEncendido());
	}

	@Test
	public void unEquipoDebeNoEstarEncendido() {
		assertTrue(!(dispositivo.estaEncendido()));
	}
}
