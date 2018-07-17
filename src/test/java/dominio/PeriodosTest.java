package dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import dominio.dispositivos.*;

import org.junit.Test;

public class PeriodosTest {

	private LocalDateTime dia1 = LocalDateTime.of(2018, 4, 1, 0, 0);
	private LocalDateTime dia2 = LocalDateTime.of(2018, 4, 2, 0, 0);
	private LocalDateTime dia4 = LocalDateTime.of(2018, 4, 4, 0, 0);
	private LocalDateTime dia5 = LocalDateTime.of(2018, 4, 5, 0, 0);
	private LocalDateTime dia5_1 = LocalDateTime.of(2018, 4, 5, 1, 0);

	@Test
	public void enUnPeriodoDe25HS_ElmetodocantidadDeHorasDebeTirar25HS() {
		
		Periodo dia4a5_1 = new Periodo(dia4, dia5_1);

		assertEquals(25, dia4a5_1.cantidadDeHoras(), 0);
	}

	@Test
	public void enUnPeriodoDe96HS_ElmetodocantidadDeDiasDebeTirar4() {
		
		Periodo dia1a5 = new Periodo(dia1, dia5);

		assertEquals(4, dia1a5.cantidadDeDias(), 0);
	}

	@Test
	public void si2PeriodosCompartenUnPeriodoDeTiempo_DebenContenerseMutuamente() {
		
		Periodo dia1a5 = new Periodo(dia1, dia5);
		Periodo dia2a5_1 = new Periodo(dia2, dia5_1);

		assertTrue(dia1a5.contiene(dia2a5_1));
		assertTrue(dia2a5_1.contiene(dia1a5));
	}

	@Test
	public void unPeriodoDebeContenerseASiMismo() {
		
		Periodo dia1a5 = new Periodo(dia1, dia5);

		assertTrue(dia1a5.contiene(dia1a5));
	}
}