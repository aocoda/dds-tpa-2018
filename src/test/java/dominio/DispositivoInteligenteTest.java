package dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Test;

import dominio.dispositivos.*;

public class DispositivoInteligenteTest {

	private DispositivoInteligente dispositivoTest = new DispositivoInteligente("dispositivoTest", 100);
	private LocalDateTime dia1 = LocalDateTime.of(2018, 4, 1, 0, 0);
	private LocalDateTime dia2 = LocalDateTime.of(2018, 4, 2, 0, 0);
	private LocalDateTime dia3 = LocalDateTime.of(2018, 4, 3, 0, 0);
	private LocalDateTime dia4 = LocalDateTime.of(2018, 4, 4, 0, 0);
	private LocalDateTime dia5 = LocalDateTime.of(2018, 4, 5, 0, 0);
	private LocalDateTime dia5_1 = LocalDateTime.of(2018, 4, 5, 1, 0);

	@Test
	public void SiUnDispositivoEstaApagado_ySeLeDieraLaOrdenDeApagarse_noDeberaHacerNadaYPermancerApagado() {

		dispositivoTest.apagar();

		assertTrue(dispositivoTest.estaApagado());
	}

	@Test
	public void SiUnDispositivoEstaEncendido_ySeLeDieraLaOrdenDeEncenderse_noDeberaHacerNadaYPermancerEncendido() {

		dispositivoTest.encender();

		assertTrue(dispositivoTest.estaEncendido());
	}

	@Test
	public void darleLaOrdenDeApagarse_DebeApagarElEquipo() {

		dispositivoTest.apagar();

		assertTrue(dispositivoTest.estaApagado());
	}

	@Test
	public void darleLaOrdenDeEncender_DebeEncenderElEquipo() {

		dispositivoTest.encender();

		assertTrue(dispositivoTest.estaEncendido());
	}

	@Test
	public void unEquipoEnAhorroNoDebeEstarApagadoNiPrendido() {

		dispositivoTest.modoAhorro();

		assertTrue(!dispositivoTest.estaApagado());
		assertTrue(!dispositivoTest.estaEncendido());
	}

	@Test
	public void unEquipoEnAhorro_DebeEncenderseSiSeLeDaLaOrdenDeEncenderse() {

		dispositivoTest.encender();

		assertTrue(dispositivoTest.estaEncendido());
	}

	@Test
	public void unHistorialConUnConsumoDe24HSQueSeEncuentraTotalmenteDentroDelPeriodoAAnalizar() {
		dispositivoTest.encender();
		Periodo dia2a3 = new Periodo(dia2, dia3);
		Periodo dia1a5 = new Periodo(dia1, dia5);
		dispositivoTest.addUso(dia2a3);

		assertEquals(2400, dispositivoTest.consumoDe(dia1a5), 0);
	}

	@Test
	public void unHistorialCon2ConsumoDe48HSQueSeEncuentraTotalmenteDentroDelPeriodoAAnalizar() {
		dispositivoTest.encender();
		Periodo dia2a3 = new Periodo(dia2, dia3);
		Periodo dia3a4 = new Periodo(dia3, dia4);
		Periodo dia1a5 = new Periodo(dia1, dia5);
		dispositivoTest.addUso(dia2a3);
		dispositivoTest.addUso(dia3a4);

		assertEquals(4800, dispositivoTest.consumoDe(dia1a5), 0);
	}

	@Test
	public void unHistorialConUnConsumoDe72HSQueSeEncuentraSolo48HsSeAnalizan_ComoParticular_TerminanLaMismaFecha() {
		dispositivoTest.encender();
		Periodo dia1a3 = new Periodo(dia1, dia3);
		Periodo dia4a5 = new Periodo(dia4, dia5);
		Periodo dia2a5 = new Periodo(dia2, dia5);
		dispositivoTest.addUso(dia1a3);
		dispositivoTest.addUso(dia4a5);

		assertEquals(4800, dispositivoTest.consumoDe(dia2a5), 0);
	}

	@Test
	public void unHistorialConUnConsumoDe72HSQueSeEncuentraSolo48HsSeAnalizan_ComoParticularUsoTerminan1HoraAntesDelPeriodo() {
		dispositivoTest.encender();
		Periodo dia1a3 = new Periodo(dia1, dia3);
		Periodo dia4a5 = new Periodo(dia4, dia5);
		Periodo dia2a5_1 = new Periodo(dia2, dia5_1);
		dispositivoTest.addUso(dia1a3);
		dispositivoTest.addUso(dia4a5);

		assertEquals(4800, dispositivoTest.consumoDe(dia2a5_1), 0);
	}

	@Test
	public void unHistorialDondeLosUsosDentroNoCaenDelPeriodoAnalizarDebeDar0() {
		dispositivoTest.encender();
		Periodo dia1a3 = new Periodo(dia1, dia3);
		Periodo dia5a5_1 = new Periodo(dia5, dia5_1);
		dispositivoTest.addUso(dia1a3);

		assertEquals(0, dispositivoTest.consumoDe(dia5a5_1), 0);
	}

	@Test
	public void unPeriodoDondeElDispositvoTieneUnaListaVaciaDebeDar0() {
		dispositivoTest.encender();
		Periodo dia5a5_1 = new Periodo(dia5, dia5_1);

		assertEquals(0, dispositivoTest.consumoDe(dia5a5_1), 0);
	}
}