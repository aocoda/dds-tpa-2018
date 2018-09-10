package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.Actuador;

public class ActuadorTest {

	@Test
	public void usandoUnActuadorQueApaga_SiLoEjecutoConUnDispositivoEncendido_SeDeberiaApagar() {
		
		Actuador apagador = dispositivo -> dispositivo.apagar();
		
		DispositivoInteligente dispositivo = new DispositivoInteligente(null, 0, 0, 0);
		
		dispositivo.encender();
		
		apagador.ejecutar(dispositivo);
		
		assertTrue(dispositivo.estaApagado());
	}
}