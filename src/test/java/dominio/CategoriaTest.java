package dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.Periodo;

public class CategoriaTest {

	private Cliente clienteTest;
	private Categoria categoriaTest;
	private Collection<DispositivoEstandar> dispositivosEstandar = new HashSet<>();
	private Periodo unMesDeTreita = new Periodo(LocalDateTime.of(2018, 1, 1, 0, 0),
			LocalDateTime.of(2018, 1, 31, 0, 0));

	public Cliente construirClienteTest() {

		return new Cliente(null, null, 0, null, null, null, null, dispositivosEstandar, new HashSet<>());
	}

	@Test
	public void conUnMesDe30DiasCargoFijoDe100CargoVariableDe2YClienteConConsumoDe100_ElEstimadoDebeSerDe6100() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 100, 1));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 100, 2, 0, 0);

		// cargoFijo + (cantHoras * consumoPorHora * cantidadDeDiasDelPeriodo *
		// cargoVariable)
		double valorEsperado = 100 + (100 * 1 * 30 * 2);

		assertEquals(valorEsperado, categoriaTest.estimadoAPagar(clienteTest, unMesDeTreita), 0);
	}

	@Test
	public void conUnClienteDeConsumoIgualA0_SeIgnoraElCargoVariableYElEstimadoDebeSerElCargoFijo() {

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 500, 100, 0, 0);

		assertEquals(500, categoriaTest.estimadoAPagar(clienteTest, unMesDeTreita), 0);
	}

	@Test
	public void conCargoFijoDe100CargoVariableDe0_SeIgnoraElConsumoDelClienteYElEstimadoDebeSerElCargoFijo() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 999, 999));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 100, 0, 0, 0);

		assertEquals(100, categoriaTest.estimadoAPagar(clienteTest, unMesDeTreita), 0);
	}

	@Test
	public void unClienteConConsumoIgualA300_NoLeCorrespondeAUnaCategoriaDe300Hasta400() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 10, 1));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 0, 0, 300, 400);

		assertTrue(!categoriaTest.leCorresponde(clienteTest, unMesDeTreita));
	}

	@Test
	public void unClienteConConsumoIgualA330_LeCorrespondeAUnaCategoriaDe329Hasta400() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 11, 1));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 0, 0, 329, 400);

		assertTrue(categoriaTest.leCorresponde(clienteTest, unMesDeTreita));
	}

	@Test
	public void unClienteConConsumoIgualA300_NoLeCorrespondeAUnaCategoriaDe200Hasta299() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 10, 1));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 0, 0, 200, 299);

		assertTrue(!categoriaTest.leCorresponde(clienteTest, unMesDeTreita));
	}

	@Test
	public void unClienteConConsumoIgualA330_LeCorrespondeAUnaCategoriaDe200Hasta330() {

		dispositivosEstandar.add(new DispositivoEstandar(null, 11, 1));

		clienteTest = construirClienteTest();
		categoriaTest = new Categoria(null, 0, 0, 200, 330);

		assertTrue(categoriaTest.leCorresponde(clienteTest, unMesDeTreita));
	}
}