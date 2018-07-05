package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.mocks.DispositivoMock2;
import dominio.reglas.Actuador;

public class ActuadorTest {

	@Test
	public void usandoUnActuadorQueConcatena_SiConcatenoBarAUnDispositivoQueTieneFoo_ElResultadoDebeSerFooBar() {
		
		Actuador<DispositivoMock2> concatenadorDeCadenas = new Actuador<DispositivoMock2>() {
			
			private String cadena = "Bar";
			
			@Override
			public void ejecutar(DispositivoMock2 dispositivoInteligente) {
				
				dispositivoInteligente.concatenar(cadena);
			}
		};
		
		DispositivoMock2 dispositivoMock = new DispositivoMock2(null, 0, "Foo");
		
		concatenadorDeCadenas.ejecutar(dispositivoMock);
		
		assertEquals("FooBar", dispositivoMock.getOtroAtributo());
	}
}