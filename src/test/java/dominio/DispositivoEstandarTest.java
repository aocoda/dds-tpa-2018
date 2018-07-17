package dominio;

import static org.junit.Assert.*;

import org.junit.Test;
import java.time.LocalDateTime;
import dominio.dispositivos.*;

public class DispositivoEstandarTest {

	DispositivoEstandar dispositivo = new DispositivoEstandar("objetoDePrueba", 10, 5, 0, 0);
	
	LocalDateTime dia0 = LocalDateTime.of(2014, 5, 1, 0, 0);
	LocalDateTime dia30 = LocalDateTime.of(2014, 5, 31, 0, 0);

	@Test
	public void unDEcon10consumoPorHoraUtilizado5HsAlDiaDebeDarEnUnPeriodoDe30DiasUnTotalDe1500() {
		
		Periodo dia0A30 = new Periodo(dia0, dia30);

		assertEquals(1500, dispositivo.consumoDe(dia0A30), 0);
	}
}