package db;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.cert.CollectionCertStoreParameters;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.Categoria;
import dominio.Cliente;
import dominio.TipoCategoria;
import dominio.TipoDocumento;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;
import dominio.geoposicionamiento.Coordenada;
import dominio.reglas.Regla;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.actuadores.Encendedor;
import dominio.reglas.condiciones.CondicionSensor;
import dominio.reglas.condiciones.relaciones.MenorA;
import dominio.reglas.sensores.Sensor;
import dominio.reglas.sensores.SensorTemperatura;
import repositorios.RepositorioActuadores;
import repositorios.RepositorioCategorias;
import repositorios.RepositorioClientes;
import repositorios.RepositorioSensores;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private RepositorioCategorias repositorioCategorias = new RepositorioCategorias();
	private RepositorioClientes repositorioclientes = new RepositorioClientes();
	
	@Test
	public void casoDePrueba1() {
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, null, new Coordenada(0, 0));
		
		entityManager().persist(cliente);
		
		Cliente clienteRecuperado = repositorioclientes.getAllInstances().stream().findFirst().get();
		
		Coordenada ubicacionNueva = new Coordenada(10,10);
		
//		clienteRecuperado.setCoordenada(ubicacionNueva);
//		
//		assertThat(clienteRecuperado.getCoordenada()).isEqualToComparingFieldByFieldRecursively(ubicacionNueva);
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
		
		Collection<DispositivoInteligente> dispositivos = Collections.singleton(dispositivo);
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, dispositivos, new Coordenada(0, 0));
		
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
		
//		dispositivoRecuperado.setNombreGenerico(nombreNuevo);
//		
//		assertThat(dispositivoRecuperado.getNombreGenerico()).isEqualTo(nombreNuevo);
	}
	
	@Test
	public void casoDePrueba3() {

		DispositivoInteligente dispositivo = new DispositivoInteligente("Estufa", 10, 0, 0);
		
		Cliente cliente = new Cliente(null, null, 0, null, null, null, null, null, Collections.singleton(dispositivo), new Coordenada(0, 0));
		
		entityManager().persist(cliente);

		Sensor sensor = new SensorTemperatura();
		
		entityManager().persist(sensor);
		
		Actuador actuador = new Encendedor();
		
		entityManager().persist(actuador);
		
		Regla regla = new Regla(new CondicionSensor(sensor, new MenorA(15)), actuador, Collections.singleton(dispositivo));
		
		cliente.agregarRegla(regla);
		
		assertThat(cliente.getReglas().size()).isEqualTo(1);
		
		
		cliente.ejecutarReglasQueCorrespondan();
		
		assertThat(dispositivo.estaEncendido()).isTrue();
		
		
		Regla reglaRecuperada = cliente.getReglas().stream().findFirst().get();
		
//		reglaRecuperada.getCondicion().setRelacion(new MenorA(20));
//		
//		assertThat(reglaRecuperada.getCondicion().getRelacion().getUnValor()).isEqualTo(20);
	}
}