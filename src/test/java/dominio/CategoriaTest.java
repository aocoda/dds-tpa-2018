package dominio;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CategoriaTest {
	
	private Cliente cliente;
	private Set<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	
	private Categoria R1 = new Categoria(SubtipoCategoria.R1, 18.76, 0.644, 0, 150);
	private Categoria R2 = new Categoria(SubtipoCategoria.R2, 35.32, 0.644, 150, 325);
	private Categoria R3 = new Categoria(SubtipoCategoria.R3, 60.71, 0.681, 325, 400);
	private Categoria R4 = new Categoria(SubtipoCategoria.R4, 71.74, 0.738, 400, 450);
	private Categoria R5 = new Categoria(SubtipoCategoria.R5, 110.38, 0.794, 450, 500);
	private Categoria R6 = new Categoria(SubtipoCategoria.R6, 220.75, 0.832, 500,600);
	private Categoria R7 = new Categoria(SubtipoCategoria.R7, 443.59, 0.851, 600, 700);
	private Categoria R8 = new Categoria(SubtipoCategoria.R8, 545.96, 0.851, 700, 1400);
	private Categoria R9 = new Categoria(SubtipoCategoria.R9, 887.19, 0.851, 1400, Double.MAX_VALUE);
	

	public Cliente construirClienteTest(Set<Dispositivo> dispositivos) {
		
		return new Cliente(null, null, 0, null, null, null, null, dispositivos);
	}
	
	@Test
	public void unClienteConConsumoIgualA500YCategoriaR5ObtieneEstimadoAPagarQueCorresponde() {
		
		dispositivos.add(new Dispositivo("Home Theater", 500, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertEquals(507.38, R5.estimadoAPagar(cliente), 0);
	}

	@Test
	public void unClienteConConsumoCeroSoloPagaElCargoFijo() {
		
		cliente = construirClienteTest(dispositivos);
		
		assertEquals(18.76, R1.estimadoAPagar(cliente), 0);
	}
	
	@Test
	public void unClienteConConsumoMenorA150CorrespondeALaCategoriaR1() {
		
		dispositivos.add(new Dispositivo("Heladera", 120, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R1.leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA150YMenorOIgualA325CorrespondeALaCategoriaR2() {
		
		dispositivos.add(new Dispositivo("Microondas", 250, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R2.leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA325YMenorOIgualA400CorrespondeALaCategoriaR3() {
		
		dispositivos.add(new Dispositivo("Lavarropas", 400, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R3.leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA400YMenorOIgualA450CorrespondeALaCategoriaR4() {
		
		dispositivos.add(new Dispositivo("Televisor", 430, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R4.leCorresponde(cliente)); 
	}
	
	@Test
	public void unClienteConConsumoMayorA450YMenorOIgualA500CorrespondeALaCategoriaR5() {
		
		dispositivos.add(new Dispositivo("Ventilador", 480, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R5.leCorresponde(cliente)); 
	}
	
	@Test
	public void unClienteConConsumoMayorA500YMenorOIgualA600CorrespondeALaCategoriaR6() {
		
		dispositivos.add(new Dispositivo("Lavavajillas", 600, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R6.leCorresponde(cliente)); 
	}
	
	@Test
	public void unClienteConConsumoMayorA600YMenorOIgualA700CorrespondeALaCategoriaR7() {
		
		dispositivos.add(new Dispositivo("Plancha", 690, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R7.leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA700YMenorOIgualA1400CorrespondeALaCategoriaR8() {
		
		dispositivos.add(new Dispositivo("Secarropa", 1000, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R8.leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA1400CorrespondeALaCategoriaR9() {
		
		dispositivos.add(new Dispositivo("Monitor", 1600, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(R9.leCorresponde(cliente));
	}
}