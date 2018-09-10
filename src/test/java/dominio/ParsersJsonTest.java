package dominio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import dominio.dispositivos.*;
import dominio.dispositivos.inteligentes.estados.Apagado;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;
import dominio.excepciones.ParserException;
import dominio.importadorJson.parser.ParserCategorias;
import dominio.importadorJson.parser.ParserClientes;
import dominio.importadorJson.parser.ParserJson;
import dominio.importadorJson.parser.ParserZonas;
import repositorios.RepositorioCategorias;
import repositorios.RepositorioClientes;

public class ParsersJsonTest {

	private RepositorioCategorias repositorioCategorias = new RepositorioCategorias();	
	
	private ParserJson<Cliente> parserClientes = new ParserClientes(repositorioCategorias);
	
	
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
		
		Collection<DispositivoEstandar> dispositivos = Collections.singletonList(new DispositivoEstandar("Heladera", 400, 1, 0, 0));
		
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
	public void tambienParseaBienCuandoElDispositivoTieneAtributosExtraALosDeSuSuperclase() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
						+ "\"tipo\": \"dominio.mocks.DispositivoMock2\","
						+ "\"nombreGenerico\": \"Generico\","
						+ "\"consumoPorHora\": 100,"
						+ "\"otroAtributo\": \"un Valor\""
					+ "}"
				+ "]"
			+ "}";
		
		DispositivoMock2 dispositivoObtenido = (DispositivoMock2) parserClientes.parsear(clienteTest).getDispositivosInteligentes().stream().findFirst().get();
		
		assertThat(dispositivoObtenido.getOtroAtributo()).isEqualTo("un Valor");
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
		
		Categoria R1 = new Categoria(TipoCategoria.R1, 0, 0, 0, 0);
		
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
	
	
	
	
	//ATRIBUTOS PARTICULARES DE LOS DISPOSITIVOS INTELIGENTES
	@Test
	public void SiElDispositivoDeUnClienteJsonVieneSinEstado_SuEstadoPorDefectoDebeSerApagado() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
						+ "\"tipo\": \"dominio.mocks.DispositivoMock\","
						+ "\"nombreGenerico\": \"Generico\","
						+ "\"consumoPorHora\": 100"
					+ "}"
				+ "]"
			+ "}";
		
		EstadoDispositivo estadoEsperado = new Apagado();
		
		DispositivoInteligente dispositivoObtenido = parserClientes.parsear(clienteTest).getDispositivosInteligentes().stream().findFirst().get();
		
		assertThat(dispositivoObtenido.getEstadoActual()).isEqualToComparingFieldByFieldRecursively(estadoEsperado);
	}
	
	@Test
	public void SiElDispositivoDeUnClienteJsonVieneSinListaDeUsos_SuListaDeUsosPorDefectoDebeSerUnaListaVacia() {
		
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
		
		assertThat(dispositivoObtenido.getHistorialUsos()).isEmpty();
	}
}