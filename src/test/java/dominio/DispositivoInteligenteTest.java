package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.*;

public class DispositivoInteligenteTest {

	private DispositivoInteligente dispositivo = new DispositivoInteligente("Heladera", 100, new Apagado());

	@Test
	public void unEquipoQueSeEnciendeTieneQueEstarEncendido() {
		dispositivo.encender();
		assertTrue(dispositivo.estaEncendido());
	}

	@Test
	public void unEquipoApagadoDebeNoEstarEncendido() {
		assertTrue(!(dispositivo.estaEncendido()));
	}
}
