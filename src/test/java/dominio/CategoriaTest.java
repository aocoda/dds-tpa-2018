package dominio;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CategoriaTest {
	
	private Cliente cliente;
	private Set<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	

	public Cliente construirClienteTest(Set<Dispositivo> dispositivos) {
		
		return new Cliente(null , null, null, null, null, dispositivos);
	}
	
	@Test
	public void unClienteConConsumoIgualA500YCategoriaR5ObtieneEstimadoAPagarQueCorresponde() {
		
		dispositivos.add(new Dispositivo("Home Theater", 500, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertEquals(507.38, new R5().estimadoAPagar(cliente), 0d);
	}

	@Test
	public void unClienteConConsumoCeroSoloPagaElCargoFijo() {
		
		cliente = construirClienteTest(dispositivos);
		
		assertEquals(18.76, new R1().estimadoAPagar(cliente), 0d);
		
		
	}
	@Test
	public void unClienteConConsumoMenorA150CorrespondeALaCategoriaR1() {
		
		dispositivos.add(new Dispositivo("Heladera", 120, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R1().leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA150YMenorOIgualA325CorrespondeALaCategoriaR2() {
		
		dispositivos.add(new Dispositivo("Microondas", 250, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R2().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA325YMenorOIgualA400CorrespondeALaCategoriaR3() {
		
		dispositivos.add(new Dispositivo("Lavarropas", 400, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R3().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA400YMenorOIgualA450CorrespondeALaCategoriaR4() {
		
		dispositivos.add(new Dispositivo("Televisor", 430, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R4().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA450YMenorOIgualA500CorrespondeALaCategoriaR5() {
		
		dispositivos.add(new Dispositivo("Ventilador", 480, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R5().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA500YMenorOIgualA600CorrespondeALaCategoriaR6() {
		
		dispositivos.add(new Dispositivo("Lavavajillas", 600, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R6().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA600YMenorOIgualA700CorrespondeALaCategoriaR7() {
		
		dispositivos.add(new Dispositivo("Plancha", 690, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R7().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA700YMenorOIgualA1400CorrespondeALaCategoriaR8() {
		
		dispositivos.add(new Dispositivo("Secarropa", 1000, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R8().leCorresponde(cliente));
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA1400CorrespondeALaCategoriaR9() {
		
		dispositivos.add(new Dispositivo("Monitor", 1600, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R9().leCorresponde(cliente));
		 
	}
}