package dominio.geoposicionamiento;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordenadaTest {

	private Coordenada bombonera = new Coordenada(-34.63565, -58.36465);
	private Coordenada comisaria24 = new Coordenada(-34.6342281,-58.3603267);
	private Coordenada obelisco = new Coordenada(-34.603739,-58.38157);
	
	@Test
	public void laBomboneraDeberiaEstarAMasDe4CuadrasDeLaComisaria24() {
		
		assertThat(bombonera.distanciaA(comisaria24)).isGreaterThan(0.4);
	}
	
	@Test
	public void laBomboneraDeberiaEstarAMenosDe4CuadrasYMediaDeLaComisaria24() {
		
		assertThat(bombonera.distanciaA(comisaria24)).isLessThan(0.45);
	}
	
	@Test
	public void laBomboneraDeberiaEstarAMasDe38CuadrasDelObelisco() {
		
		assertThat(bombonera.distanciaA(obelisco)).isGreaterThan(3.8);
	}
	
	@Test
	public void laBomboneraDeberiaEstarAMenosDe40CuadrasDelObelisco() {
		
		assertThat(bombonera.distanciaA(obelisco)).isLessThan(4);
	}
}