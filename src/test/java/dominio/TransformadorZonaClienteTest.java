package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import dominio.excepciones.SinTransformadoresCercanosException;
import dominio.geoposicionamiento.Area;
import dominio.geoposicionamiento.Coordenada;

public class TransformadorZonaClienteTest {

	@Test
	public void unClienteEstaAsociadoAlTransformadorMasCercanoQueTenga() {
		
		Coordenada coordenadaCliente = mock(Coordenada.class);
		Coordenada coordenadaCercana = mock(Coordenada.class);
		Coordenada coordenadaLejana = mock(Coordenada.class);

		when(coordenadaCercana.distanciaA(coordenadaCliente)).thenReturn(10d);
		when(coordenadaLejana.distanciaA(coordenadaCliente)).thenReturn(11d);
		
		Transformador transformadorCercano = new Transformador(null, coordenadaCercana);
		Transformador transformadorLejano = new Transformador(null, coordenadaLejana);
		
		List<Transformador> transformadores = Arrays.asList(transformadorCercano, transformadorLejano);
		
		Cliente cliente = construirCliente(coordenadaCliente);

		assertEquals(transformadorCercano, cliente.transformadorAsociado(transformadores));
	}
	
	@Test(expected = SinTransformadoresCercanosException.class)
	public void siNoHayTransofrmadoresNoSeLePuedeAsociarNingunoAUnCliente() {
		
		Coordenada coordenadaCliente = mock(Coordenada.class);
		
		List<Transformador> transformadores = Collections.emptyList();
		
		Cliente cliente = construirCliente(coordenadaCliente);

		cliente.transformadorAsociado(transformadores);
	}
	
	@Test
	public void si2ClientesTieneAsociadoUnMismoTransformadorEseTransformadorDeberiaTenerAsociadosEsosClientes() {
		
		Transformador transformadorDeInteres = new Transformador(null, null);
		Transformador t2 = mock(Transformador.class);
		Transformador t3 = mock(Transformador.class);
		
		Cliente cliente1 = mock(Cliente.class);
		Cliente cliente2 = mock(Cliente.class);
		Cliente cliente3 = mock(Cliente.class);
		
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2, cliente3);
		List<Transformador> transformadores = Arrays.asList(transformadorDeInteres, t2, t3);
		
		when(cliente1.transformadorAsociado(transformadores)).thenReturn(transformadorDeInteres);
		when(cliente2.transformadorAsociado(transformadores)).thenReturn(transformadorDeInteres);
		when(cliente3.transformadorAsociado(transformadores)).thenReturn(t2);
		
		assertTrue(transformadorDeInteres
				.clientesAsociados(clientes, transformadores)
				.containsAll(Arrays.asList(cliente1, cliente2)));
	}
	
	@Test
	public void unaZonaTieneAsociadosSoloLosTransformadoresQueEstanDentroDeSuArea() {
		
		Area area = mock(Area.class);
		
		Zona zona = new Zona(null, area);
		
		Transformador t1 = new Transformador(null, new Coordenada(0, 0));
		Transformador t2 = new Transformador(null, new Coordenada(0, 0));
		Transformador t3 = new Transformador(null, new Coordenada(0, 0));
		
		when(area.contieneA(t1.getCoordenada())).thenReturn(true);
		when(area.contieneA(t2.getCoordenada())).thenReturn(true);
		when(area.contieneA(t3.getCoordenada())).thenReturn(false);
		
		List<Transformador> transformadores = Arrays.asList(t1, t2, t3);
		
		assertTrue(zona.transformadoresAsociados(transformadores).containsAll(Arrays.asList(t1, t2)));
	}

	private Cliente construirCliente(Coordenada coordenada) {
		
		return new Cliente(null, null, 0, null, null, null, null, null, null, coordenada, null, null);
	}
}