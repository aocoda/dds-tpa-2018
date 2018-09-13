package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.actuadores.Apagador;

public class ActuadorTest {

	@Test
	public void usandoUnActuadorQueApaga_SiLoEjecutoConUnDispositivoEncendido_SeDeberiaApagar() {
		
		Actuador apagador = new Apagador();
		
		DispositivoInteligente dispositivo = new DispositivoInteligente(null, 0, 0, 0);
		
		dispositivo.encender();
		
		apagador.ejecutar(dispositivo);
		
		assertTrue(dispositivo.estaApagado());
	}
}