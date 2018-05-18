package dominio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import dominio.dispositivos.*;
import dominio.excepciones.ParserException;
import dominio.importadorJson.ParserJson;
import dominio.mocks.DispositivoMock;
import dominio.importadorJson.ParserCategorias;
import dominio.importadorJson.ParserClientes;
import repositorios.RepositorioCategorias;

public class ParsersJsonTest {

	private RepositorioCategorias repositorioCategorias = new RepositorioCategorias();
	
	private ParserJson<Categoria> parserCategorias = new ParserCategorias();
	private ParserJson<Cliente> parserClientes = new ParserClientes(repositorioCategorias);
	
	
	
	//CATEGORIAS
	@Test
	public void SiLaCategoriaJsonTieneTodosLosAtributos_SeCreaUnaCategoriaConEsosValores() {
		
		String categoriaTest = "{ "
					+ "\"subtipoCategoria\": \"R1\","
					+ "\"cargoFijoMensual\" : 100,"
					+ "\"cargoVariable\" : 100,"
					+ "\"consumoDesde\": 0,"
					+ "\"consumoHasta\": 150"
				+ " }";
				
		Categoria categoriaEsperada = new Categoria(SubtipoCategoria.R1, 100, 100, 0, 150);
		
		assertThat(parserCategorias.parsear(categoriaTest)).isEqualToComparingFieldByFieldRecursively(categoriaEsperada);
	}
	
	@Test
	public void SiLaCategoriaJsonNoTieneAlgunAtributo_SeCreaUnaCategoriaConValoresPorDefectoCeroONull() {
		
		String categoriaTest = "{ "
					+ "\"cargoFijoMensual\" : 100,"
					+ "\"cargoVariable\" : 100,"
					+ "\"consumoDesde\": 0"
				+ " }";
				
		Categoria categoriaEsperada = new Categoria(null, 100, 100, 0, 0);
		
		assertThat(parserCategorias.parsear(categoriaTest)).isEqualToComparingFieldByFieldRecursively(categoriaEsperada);
	}
	
	@Test
	public void SiLaCategoriaJsonEstaMalFormadaEntonces_ArrojaParserException() {
		
		String categoriaTest = "{ "
				+ "\"subtipoCategoria\": \"R1\","
				+ "\"cargoFijoMensual\" : 100,"
				+ "\"cargoVariable\" : 100,"
				+ "\"consumoDesde\": 0,"
				+ "\"consumoHasta\": 150";
		
		assertThatThrownBy(() -> parserCategorias.parsear(categoriaTest)).isInstanceOf(ParserException.class);
	}
	
	
	
	
	//CLIENTES
	@Test
	public void SiElClienteJsonTieneElTipoDocumento_SeCreaUnClienteConElTipoCorrespondienteYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"tipoDocumento\": \"LC\" }";
				
		Cliente clienteEsperado = new Cliente(null, TipoDocumento.LC, 0, null, null, null, null, null, null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaListaDeDispositivosEstandarVacia_SeCreaUnClienteConUnaListaDeDispositivosEstandarVaciaYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"dispositivosEstandar\": [ ] }";
				
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, null, null, Collections.emptyList(), null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaListaDeDispositivosInteligentesVacia_SeCreaUnClienteConUnaListaDeDispositivosInteligentesVaciaYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"dispositivosInteligentes\": [ ] }";
				
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, null, null, null, Collections.emptyList());
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaListaDeDispositivosEstandarConElementos_SeCreaUnClienteConUnaEsaListaYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{\"dispositivosEstandar\":"
				+ "["
					+ "{"
						+ "\"nombreGenerico\": \"Heladera\","
						+ "\"consumoPorHora\": 400,"
						+ "\"horasEstimadasDeUsoPorDia\": 1"
					+ "}"
				+ "]"
			+ "}";
		
		Collection<DispositivoEstandar> dispositivos = Collections.singletonList(new DispositivoEstandar("Heladera", 400,1));
		
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, null, null, dispositivos, null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaListaDeDispositivosInteligentesConUnElementoDelTipoMock_SeCreaUnClienteConUnaListaConUnElementoDeEseTipo() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
						+ "\"tipo\": \"dominio.mocks.DispositivoMock\","
						+ "\"nombreGenerico\": \"Generico\","
						+ "\"consumoPorHora\": 100"
					+ "}"
				+ "]"
			+ "}";
		
		DispositivoInteligente dispositivoObtenido = parserClientes.parsear(clienteTest).getDispositivosInteligentes().stream().findFirst().get();
		
		assertThat(dispositivoObtenido).isInstanceOf(DispositivoMock.class);
	}
		
	@Test
	public void SiElClienteJsonTieneUnaCategoriaInexistenteEnElRepo_SeCreaUnClienteConCategoriaNullYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"categoria\": \"R1\" }";
		
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, null, null, null, null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaCategoriaQueSiExisteEnElRepo_SeCreaUnClienteConSuCategoriaYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"categoria\": \"R1\" }";
		
		Categoria R1 = new Categoria(SubtipoCategoria.R1, 0, 0, 0, 0);
		
		repositorioCategorias.agregar(R1);
		
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, null, R1, null, null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaFechaDeAltaValida_SeCreaUnClienteConSuFechaYLosDemasValoresPorDefectoCeroONull() {
		
		String clienteTest = "{ \"fechaAltaServicio\": \"2017-01-01\" }";
		
		Cliente clienteEsperado = new Cliente(null, null, 0, null, null, LocalDate.of(2017, 1, 1), null, null, null);
		
		assertThat(parserClientes.parsear(clienteTest)).isEqualToComparingFieldByFieldRecursively(clienteEsperado);
	}
	
	@Test
	public void SiElClienteJsonTieneUnaFechaDeAltaMalFormada_ArrojaParserException() {
		
		String clienteTest = "{ \"fechaAltaServicio\": \"2017-01-\" }";
		
		assertThatThrownBy(() -> parserClientes.parsear(clienteTest)).isInstanceOf(ParserException.class);
	}
}