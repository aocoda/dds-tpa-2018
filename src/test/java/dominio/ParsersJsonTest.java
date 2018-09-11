package dominio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

import dominio.dispositivos.*;
import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.Apagado;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;
import dominio.excepciones.ParserException;
import dominio.importadorJson.parser.ParserClientes;
import dominio.importadorJson.parser.ParserJson;
import repositorios.RepositorioCategorias;

public class ParsersJsonTest {

	private RepositorioCategorias repositorioCategorias = new RepositorioCategorias();	
	
	private ParserJson<Cliente> parserClientes = new ParserClientes(repositorioCategorias);
	
	
	//CLIENTES
	@Test
	public void SiElJsonTieneElTipoDocumento_SeCreaUnClienteConElTipoCorrespondiente() {
		
		String clienteTest = "{ \"tipoDocumento\": \"LC\" }";
		
		assertThat(parserClientes.parsear(clienteTest).getTipoDocumento()).isEqualTo(TipoDocumento.LC);
	}
	
	@Test
	public void SiElJsonTieneUnaListaDeDispositivosEstandarVacia_SeCreaUnClienteConUnaListaDeDispositivosEstandarVacia() {
		
		String clienteTest = "{ \"dispositivosEstandar\": [ ] }";
		
		assertThat(parserClientes.parsear(clienteTest).getDispositivosEstandar()).isEqualTo(Collections.emptyList());
	}
	
	@Test
	public void SiElJsonTieneUnaListaDeDispositivosInteligentesVacia_SeCreaUnClienteConUnaListaDeDispositivosInteligentesVacia() {
		
		String clienteTest = "{ \"dispositivosInteligentes\": [ ] }";
		
		assertThat(parserClientes.parsear(clienteTest).getDispositivosInteligentes()).isEqualTo(Collections.emptyList());
	}
	
	@Test
	public void SiElJsonTieneUnaListaDeDispositivosEstandarConElementos_SeCreaUnClienteConEsaLista() {
		
		String clienteTest = "{\"dispositivosEstandar\":"
				+ "["
					+ "{"
						+ "\"nombreGenerico\": \"Heladera\","
						+ "\"consumoPorHora\": 400,"
						+ "\"horasEstimadasDeUsoPorDia\": 1,"
						+ "\"horasDeUsoMinimo\": 0,"
						+ "\"horasDeUsoMaximo\": 0"
					+ "}"
				+ "]"
			+ "}";
		
		DispositivoEstandar dispositivo = new DispositivoEstandar("Heladera", 400, 1, 0, 0);
		
		assertThat(parserClientes.parsear(clienteTest).getDispositivosEstandar())
					.usingRecursiveFieldByFieldElementComparator()
					.containsExactly(dispositivo);
	}
	
	@Test
	public void SiElJsonTieneUnaListaDeDispositivosInteligentesConElementos_SeCreaUnClienteConEsaLista() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
						+ "\"nombreGenerico\": \"Cafetera\","
						+ "\"consumoPorHora\": 300,"
						+ "\"horasDeUsoMinimo\": 0,"
						+ "\"horasDeUsoMaximo\": 0"
					+ "}"
				+ "]"
			+ "}";
		
		DispositivoInteligente dispositivo = new DispositivoInteligente("Cafetera", 300, 0, 0);
		
		assertThat(parserClientes.parsear(clienteTest).getDispositivosInteligentes())
					.usingRecursiveFieldByFieldElementComparator()
					.containsExactly(dispositivo);
	}
	
	@Test
	public void SiElJsonTieneUnaCategoriaInexistenteEnElRepo_SeCreaUnClienteConCategoriaNull() {
		
		String clienteTest = "{ \"categoria\": \"R1\" }";
		
		assertThat(parserClientes.parsear(clienteTest).getCategoria()).isEqualTo(null);
	}
	
	@Test
	public void SiElJsonTieneUnaCategoriaQueSiExisteEnElRepositorio_SeCreaUnClienteConEsaCategoria() {
		
		String clienteTest = "{ \"categoria\": \"R1\" }";
		
		Categoria R1 = new Categoria(TipoCategoria.R1, 0, 0, 0, 0);
		
		repositorioCategorias.agregar(R1);
		
		assertThat(parserClientes.parsear(clienteTest).getCategoria()).isEqualTo(R1);
	}
	
	@Test
	public void SiElJsonTieneUnaFechaDeAltaValida_SeCreaUnClienteConEsaFecha() {
		
		String clienteTest = "{ \"fechaAltaServicio\": \"2017-01-01\" }";
		
		assertThat(parserClientes.parsear(clienteTest).getFechaAltaServicio()).isEqualTo(LocalDate.of(2017, 1, 1));
	}
	
	@Test
	public void SiElJsonTieneUnaFechaDeAltaMalFormada_ArrojaParserException() {
		
		String clienteTest = "{ \"fechaAltaServicio\": \"2017-01-\" }";
		
		assertThatThrownBy(() -> parserClientes.parsear(clienteTest)).isInstanceOf(ParserException.class);
	}
	
	@Test
	public void SiElDispositivoDeUnJsonVieneSinEstado_SuEstadoPorDefectoDebeSerApagado() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
					+ "\"nombreGenerico\": \"Microondas\","
					+ "\"consumoPorHora\": 50,"
					+ "\"horasDeUsoMinimo\": 0,"
					+ "\"horasDeUsoMaximo\": 0"
					+ "}"
				+ "]"
			+ "}";
		
		EstadoDispositivo estadoEsperado = new Apagado();
		
		EstadoDispositivo estadoObtenido = parserClientes
				.parsear(clienteTest)
				.getDispositivosInteligentes()
				.stream()
				.findFirst()
				.get()
				.getEstadoActual();
		
		assertThat(estadoObtenido).isEqualToComparingFieldByFieldRecursively(estadoEsperado);
	}
	
	@Test
	public void SiElDispositivoJsonVieneSinHistorialDeUsos_SuHistorialDeUsosPorDefectoDebeSerUnaListaVacia() {
		
		String clienteTest = "{\"dispositivosInteligentes\":"
				+ "["
					+ "{"
						+ "\"nombreGenerico\": \"Lavarropas\","
						+ "\"consumoPorHora\": 100,"
						+ "\"horasDeUsoMinimo\": 0,"
						+ "\"horasDeUsoMaximo\": 0"
					+ "}"
				+ "]"
			+ "}";	
		
		Collection<Uso> historialObtenido = parserClientes
				.parsear(clienteTest)
				.getDispositivosInteligentes()
				.stream()
				.findFirst()
				.get()
				.getHistorialUsos();
		
		assertThat(historialObtenido).isEmpty();
	}
}