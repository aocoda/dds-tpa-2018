package dominio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

import dominio.asesorDeUso.AsesorDeUso;
import dominio.asesorDeUso.Recomendacion;
import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.reglas.condiciones.CondicionSimplex;


public class AsesorDeUsoTest {

	@Test
	public void getHorasOptimasParaDosDispositivos() {
		
		DispositivoInteligente d1 = new DispositivoInteligente("aire", 1.613, 90, 360);
		
		DispositivoInteligente d2 = new DispositivoInteligente("microondas", 0.64, 3, 15);
		
		List<Dispositivo> dispositivos = Arrays.asList(d1, d2);
		
		
		Collection<Recomendacion> resultado = new AsesorDeUso().getHorasOptimas(dispositivos);
		
		
		assertThat(resultado)
			.usingRecursiveFieldByFieldElementComparator()
			.containsExactly(new Recomendacion(d1, 360), new Recomendacion(d2, 15));
	}
	
	@Test
	public void recomendacionesParaDosDispositivosConUsos() {
		
		DispositivoInteligente d1 = new DispositivoInteligente("aire", 1.613, 90, 360);
		
		DispositivoInteligente d2 = new DispositivoInteligente("microondas", 0.64, 3, 15);
		
		List<Dispositivo> dispositivos = Arrays.asList(d1, d2);

		Periodo periodo1 = Periodo.deLasUltimasNHoras(200);

		d1.agregarUso(periodo1);

		Periodo periodo2 = Periodo.deLasUltimasNHoras(10);

		d2.agregarUso(periodo2);
		
		
		AsesorDeUso asesor = new AsesorDeUso();
		
		Periodo periodo = Periodo.deLosUltimosNMeses(1);

		Collection<Recomendacion> resultado = asesor.recomendacionesPara(dispositivos, periodo);
		
		
		assertThat(resultado)
			.usingRecursiveFieldByFieldElementComparator()
			.containsExactly(new Recomendacion(d1, 160), new Recomendacion(d2, 5));
	}
	
	@Test
	public void condicionSimplex() {
		
		DispositivoInteligente dispositivo1 = new DispositivoInteligente("dispositivoTest", 10, 0, 50);
		
		dispositivo1.agregarUso(Periodo.deLasUltimasNHoras(100));
		
		DispositivoInteligente dispositivo2 = new DispositivoInteligente("dispositivoTest2", 20, 0, 0);
		
		List<Dispositivo> dispositivos = Arrays.asList(dispositivo1, dispositivo2);
		
		
		AsesorDeUso asesor = new AsesorDeUso();
		
		Periodo periodo = Periodo.deLosUltimosNMeses(1);
		
		CondicionSimplex condicion = new CondicionSimplex(asesor, dispositivo1, dispositivos, periodo);
		
		
		assertThat(condicion.seCumple()).isTrue();
	}
}