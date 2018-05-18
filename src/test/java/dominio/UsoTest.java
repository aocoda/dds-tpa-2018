package dominio;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import dominio.dispositivos.*;
import dominio.dispositivos.inteligentes.*;
import dominio.dispositivos.inteligentes.estados.*;
import org.junit.Test;

public class UsoTest {

	private LocalDateTime dia1 = LocalDateTime.of(2018, 4, 1, 0, 0);
	private LocalDateTime dia2 = LocalDateTime.of(2018, 4, 2, 0, 0);
	private LocalDateTime dia3 = LocalDateTime.of(2018, 4, 3, 0, 0);
	private LocalDateTime dia4 = LocalDateTime.of(2018, 4, 4, 0, 0);
	private LocalDateTime dia5 = LocalDateTime.of(2018, 4, 5, 0, 0);
	private LocalDateTime dia5_1 = LocalDateTime.of(2018, 4, 5, 1, 0);

	@Test
	public void unPeriodoDia1ADia5AcotadoAUnPeriodoDeDia2A4_DebeQuedar2A4() {
		Periodo dia1a5 = new Periodo(dia1, dia5);
		Periodo dia2a4 = new Periodo(dia2, dia4);
		Uso test0 = new Uso(dia1a5, new Encendido());
		Uso test1 = test0.acotarExtremos(dia2a4);

		assertTrue(dia2.isEqual(test1.getPeriodo().getFechaYHoraDeInicio()));
		assertTrue(dia4.isEqual(test1.getPeriodo().getFechaYHoraDeFin()));
	}

	@Test
	public void unPeriodoDia2ADia4AcotadoAUnPeriodoDeDia1A5_DebeQuedar2A4() {
		Periodo dia1a5 = new Periodo(dia1, dia5);
		Periodo dia2a4 = new Periodo(dia2, dia4);
		Uso test0 = new Uso(dia2a4, new Encendido());
		Uso test1 = test0.acotarExtremos(dia1a5);

		assertTrue(dia2.isEqual(test1.getPeriodo().getFechaYHoraDeInicio()));
		assertTrue(dia4.isEqual(test1.getPeriodo().getFechaYHoraDeFin()));
	}

	@Test
	public void unPeriodoDia1ADia5AcotadoAUnPeriodoDeDia3A5_DebeQuedar3A5() {
		Periodo dia1a5 = new Periodo(dia1, dia5);
		Periodo dia3a5 = new Periodo(dia3, dia5);
		Uso test0 = new Uso(dia1a5, new Encendido());
		Uso test1 = test0.acotarExtremos(dia3a5);

		assertTrue(dia3.isEqual(test1.getPeriodo().getFechaYHoraDeInicio()));
		assertTrue(dia5.isEqual(test1.getPeriodo().getFechaYHoraDeFin()));
	}

	@Test
	public void unPeriodoDia1ADia5AcotadoAUnPeriodoDeDia1A5_DebeQuedar1A5() {
		Periodo dia1a5 = new Periodo(dia1, dia5);
		Uso test0 = new Uso(dia1a5, new Encendido());
		Uso test1 = test0.acotarExtremos(dia1a5);

		assertTrue(dia1.isEqual(test1.getPeriodo().getFechaYHoraDeInicio()));
		assertTrue(dia5.isEqual(test1.getPeriodo().getFechaYHoraDeFin()));
	}

	@Test
	public void unPeriodoDia1ADia3AcotadoAUnPeriodoDeDia2A4_DebeQuedar2A3() {
		Periodo dia1a3 = new Periodo(dia1, dia3);
		Periodo dia2a4 = new Periodo(dia2, dia4);
		Uso test0 = new Uso(dia1a3, new Encendido());
		Uso test1 = test0.acotarExtremos(dia2a4);

		assertTrue(dia2.isEqual(test1.getPeriodo().getFechaYHoraDeInicio()));
		assertTrue(dia3.isEqual(test1.getPeriodo().getFechaYHoraDeFin()));
	}

	@Test
	public void unperiodoDe25HsconUnConsumoDe10DebeRetornar250() {
		Periodo dia4a5_1 = new Periodo(dia4, dia5_1);
		Uso test = new Uso(dia4a5_1, new Encendido());

		assertEquals(250, test.consumo(10), 0);
	}
}