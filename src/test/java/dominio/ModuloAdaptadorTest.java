package dominio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.adaptadores.ModuloAdaptador;
import dominio.excepciones.DispositivoNoAdaptadoException;
import dominio.mocks.DispositivoMock2;

public class ModuloAdaptadorTest {

	private DispositivoEstandar dispositivoATransformar = new DispositivoEstandar("Estandar", 10, 10);
	
	private Collection<DispositivoEstandar> dispEstandar = new ArrayList<DispositivoEstandar>(Arrays.asList(dispositivoATransformar));
	
	private Collection<DispositivoInteligente> dispInteligentes = new ArrayList<>();
	
	private Cliente cliente = new Cliente(null, null, 0, null, null, null, null, dispEstandar, dispInteligentes);
	
	
	@Test
	public void conUnModuloQueNoFalla_ElDispositivoDebeTransformarsYeRemoverseDeUnaListaYAgregarseEnLaOtra() {

		ModuloAdaptador moduloQueAnda = new ModuloAdaptador() {
			
			private String nuevaCadena = "Un String para Concatenar";
			
			@Override
			public DispositivoInteligente adaptar(DispositivoEstandar dispositivo) {
				
				return new DispositivoMock2("Dispositivo Adaptado", 100, nuevaCadena);
			}
		};
		
		cliente.transformar(dispositivoATransformar, moduloQueAnda);
		
		assertEquals(dispEstandar.size(), 0);
		assertEquals(dispInteligentes.size(), 1);
	}
	
	@Test(expected = DispositivoNoAdaptadoException.class)
	public void conUnModuloQueFalla_ElDispositivoNoSeTransformaNiSeCambiaDeLista() {

		ModuloAdaptador moduloQueNoAnda = new ModuloAdaptador() {
			
			@Override
			public DispositivoInteligente adaptar(DispositivoEstandar dispositivo) {
				
				throw new DispositivoNoAdaptadoException("No se pudo adaptar el dispositivo", new NullPointerException());
			}
		};
		
		cliente.transformar(dispositivoATransformar, moduloQueNoAnda);
	}
}