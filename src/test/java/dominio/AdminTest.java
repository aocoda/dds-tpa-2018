package dominio;

import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Test;

public class AdminTest {
	
	private Admin admin;
	
	@Test
	public void siUnAdministradorEntraHac4meses_ElMetodo_antiguedad_DebeArrojar4(){
		
		LocalDate fecha = LocalDate.now().minusMonths(4);
		admin = new Admin(null, null, null,fecha);
		
		assertEquals(4, admin.antiguedad());
	}
}