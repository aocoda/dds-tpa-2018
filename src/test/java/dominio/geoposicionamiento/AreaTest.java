package dominio.geoposicionamiento;

import static org.junit.Assert.*;

import org.junit.Test;

public class AreaTest {

	private Coordenada utnMedrano = new Coordenada(-34.598578, -58.419906);
	private Coordenada abastoShopping = new Coordenada(-34.603879, -58.410998);
	
	//distancia entre utnMedrano y abasto shopping es de 1.01 km
	
	@Test
	public void unAreaDe9CuadrasDeRadioDesdeUtnMedranoNoDebeContenerAAbastoShopping() {
	
		Area area = new Area(utnMedrano, 0.9);
		
		assertTrue(!area.contieneA(abastoShopping));
	}

	@Test
	public void unAreaDe11CuadrasDeRadioDesdeUtnMedranoDebeContenerAAbastoShopping() {
	
		Area area = new Area(utnMedrano, 1.1);
		
		assertTrue(area.contieneA(abastoShopping));
	}
}