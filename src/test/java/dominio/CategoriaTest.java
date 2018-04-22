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
	public void unClienteConConsumoMenorA150CorrespondeALaCategoriaR1() {
		
		dispositivos.add(new Dispositivo("Heladera", 120, false));
		
		cliente = construirClienteTest(dispositivos);
		
		assertTrue(new R1().leCorresponde(cliente));
	}
	
	@Test
	public void unClienteConConsumoMayorA150YMenorOIgualA325CorrespondeALaCategoriaR2() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA325YMenorOIgualA400CorrespondeALaCategoriaR3() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA400YMenorOIgualA450CorrespondeALaCategoriaR4() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA450YMenorOIgualA500CorrespondeALaCategoriaR5() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA500YMenorOIgualA600CorrespondeALaCategoriaR6() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA600YMenorOIgualA700CorrespondeALaCategoriaR7() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA700YMenorOIgualA1400CorrespondeALaCategoriaR8() {
		 
	}
	
	@Test
	public void unClienteConConsumoMayorA1400CorrespondeALaCategoriaR9() {
		 
	}
}