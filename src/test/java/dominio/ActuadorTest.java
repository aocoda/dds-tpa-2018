package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.mocks.DispositivoMock;
import dominio.reglas.Actuador;

public class ActuadorTest {

	@Test
	public void usandoUnActuadorQueApaga_SiLoEjecutoConUnDispositivoEncendido_SeDeberiaApagar() {
		
		Actuador apagador = dispositivo -> dispositivo.apagar();
		
		DispositivoMock dispositivoMock = new DispositivoMock(null, 0);
		
		dispositivoMock.encender();
		
		apagador.ejecutar(dispositivoMock);
		
		assertTrue(dispositivoMock.estaApagado());
	}
}