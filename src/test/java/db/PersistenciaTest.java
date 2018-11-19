package db;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.Cliente;
import dominio.Transformador;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.dispositivos.inteligentes.EstadoDispositivo;
import dominio.geoposicionamiento.Coordenada;
import dominio.importadorJson.parser.ParserTransformadores;
import dominio.reglas.Regla;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.actuadores.Encendedor;
import dominio.reglas.condiciones.Condicion;
import dominio.reglas.condiciones.CondicionSensor;
import dominio.reglas.condiciones.Relacion;
import dominio.reglas.sensores.Sensor;
import dominio.reglas.sensores.SensorTemperatura;
import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private RepositorioClientes repositorioclientes = new RepositorioClientes();
	private RepositorioTransformadores repositorioTransformadores = new RepositorioTransformadores();
	
	@Test
	public void casoDePrueba1() {
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, null, new Coordenada(0, 0), null, null);
		
		entityManager().persist(cliente);
		
		Cliente clienteRecuperado = repositorioclientes.getAllInstances().stream().findFirst().get();
		
		Coordenada ubicacionNueva = new Coordenada(10,10);
		
		clienteRecuperado.setCoordenada(ubicacionNueva);
		
		assertThat(clienteRecuperado.getCoordenada()).isEqualToComparingFieldByFieldRecursively(ubicacionNueva);
	}
	
	@Test
	public void casoDePrueba2() {
		
		DispositivoInteligente dispositivo = new DispositivoInteligente("Heladera", 100, 10, 20);
		dispositivo.encender();
		Periodo periodoDe10Hs = new Periodo(LocalDateTime.of(2018, 9, 13, 0, 0), LocalDateTime.of(2018, 9, 13, 10, 0));
		dispositivo.agregarUso(periodoDe10Hs);
		dispositivo.modoAhorro();
		Periodo periodoDe1Hs = new Periodo(LocalDateTime.of(2018, 9, 13, 10, 0), LocalDateTime.of(2018, 9, 13, 11, 0));
		dispositivo.agregarUso(periodoDe1Hs);
		dispositivo.encender();
		Periodo periodoDeMediaHs = new Periodo(LocalDateTime.of(2018, 9, 13, 11, 0), LocalDateTime.of(2018, 9, 13, 11, 30));
		dispositivo.agregarUso(periodoDeMediaHs);
		
		List<DispositivoInteligente> dispositivos = Collections.singletonList(dispositivo);
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, dispositivos, new Coordenada(0, 0), null, null);
		
		entityManager().persist(cliente);
		
		DispositivoInteligente dispositivoRecuperado = repositorioclientes
				.getAllInstances().stream().findFirst().get()
				.getDispositivosInteligentes().stream().findFirst().get();
		
		List<Double> horasEncendido = dispositivoRecuperado
				.getHistorialUsos().stream()
				.filter(u -> u.getEstadoDispositivo().equals(EstadoDispositivo.ENCENDIDO))
				.map(uso -> uso.getPeriodo().cantidadDeHoras())
				.collect(Collectors.toList());
		
		assertThat(horasEncendido).containsExactly(10d, 0.5d);
		
		String nombreNuevo = "Ya no soy mas heladera";
		
		dispositivoRecuperado.setNombreGenerico(nombreNuevo);
		
		assertThat(dispositivoRecuperado.getNombreGenerico()).isEqualTo(nombreNuevo);
	}
	
	@Test
	public void casoDePrueba3() {

		DispositivoInteligente dispositivo = new DispositivoInteligente("Estufa", 10, 0, 0);
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, Collections.singletonList(dispositivo), new Coordenada(0, 0), null, null);
		
		entityManager().persist(cliente);

		Sensor sensor = new SensorTemperatura();
		
		entityManager().persist(sensor);
		
		Actuador actuador = new Encendedor();
		
		entityManager().persist(actuador);
		
		Regla regla = new Regla(new CondicionSensor(sensor, Relacion.MENOR_A, 15), actuador, Collections.singletonList(dispositivo));
		
		cliente.agregarRegla(regla);
		
		assertThat(cliente.getReglas().size()).isEqualTo(1);
		
		
		cliente.ejecutarReglasQueCorrespondan();
		
		assertThat(dispositivo.estaEncendido()).isTrue();
		
		
		Regla reglaRecuperada = cliente.getReglas().stream().findFirst().get();
		
		Condicion nuevaCondicion = new CondicionSensor(sensor, Relacion.MENOR_A, 20);
		
		reglaRecuperada.setCondicion(nuevaCondicion);
		
		assertThat(reglaRecuperada.getCondicion()).isEqualTo(nuevaCondicion);
	}
	
	@Test
	public void casoDePrueba4() {
		
		Transformador transformador = new Transformador("transformador1", new Coordenada(1,2));
		
		entityManager().persist(transformador);
		
		Transformador transformador2 = new Transformador("tranformador2", new Coordenada(3,4)); 
		
		entityManager().persist(transformador2);
		
		int cantidadInicial = repositorioTransformadores.getAllInstances().size();
		
		String transformador3Json = "{"
				+ "\"nombre\": \"transformador3\","
				+ "\"coordenada\":"
					+ "{"
						+ "\"latitud\": 5,"
						+ "\"longitud\": 6"
					+ "}"
				+ "}";

		Transformador transformador3 = new ParserTransformadores().parsear(transformador3Json);
		
		entityManager().persist(transformador3);
		
		int cantidadFinal = repositorioTransformadores.getAllInstances().size();
		
		assertThat(cantidadFinal).isEqualTo(cantidadInicial + 1);
	}
	
	@Test
	public void casoDePrueba5() {
		
		DispositivoInteligente dispositivo = new DispositivoInteligente("Televisor", 10, 0, 0);
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, Collections.emptyList(), Collections.singletonList(dispositivo), new Coordenada(0, 0), null, null);
	
		entityManager().persist(cliente);
		
		dispositivo.encender();
		Periodo periodoDe2Hs = new Periodo(LocalDateTime.of(2018, 9, 13, 0, 0), LocalDateTime.of(2018, 9, 13, 2, 0));
		dispositivo.agregarUso(periodoDe2Hs);
		
		double consumoDelHogarEnElPeriodo = repositorioclientes
				.getAllInstances()
				.stream()
				.findFirst()
				.get()
				.consumoDe(periodoDe2Hs);

		assertThat(consumoDelHogarEnElPeriodo).isEqualTo(20.0);
		
		Transformador transformador = new Transformador("transformador", new Coordenada(1,1));
		
		entityManager().persist(transformador);
		
		double consumoDelTransformadorEnElPeriodo = repositorioTransformadores
				.getAllInstances()
				.stream()
				.findFirst()
				.get()
				.consumoDe(periodoDe2Hs, repositorioclientes.getAllInstances(), repositorioTransformadores.getAllInstances());
		
		assertThat(consumoDelTransformadorEnElPeriodo).isEqualTo(20.0);
		
		DispositivoInteligente dispositivoRecuperado = repositorioclientes
				.getAllInstances().stream().findFirst().get()
				.getDispositivosInteligentes().stream().findFirst().get();
		
		double consumoAnterior = dispositivoRecuperado.getConsumoPorHora();
		dispositivoRecuperado.setConsumoPorHora(consumoAnterior * 11);
		
		double nuevoConsumoDelTransformadorEnElPeriodo = repositorioTransformadores
				.getAllInstances()
				.stream()
				.findFirst()
				.get()
				.consumoDe(periodoDe2Hs, repositorioclientes.getAllInstances(), repositorioTransformadores.getAllInstances());
		
		assertThat(nuevoConsumoDelTransformadorEnElPeriodo).isEqualTo(consumoDelTransformadorEnElPeriodo * 11);
	}
}