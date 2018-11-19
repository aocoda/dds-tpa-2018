package dominio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class AdministradorTest {

	private Administrador admin;

	@Test
	public void siUnAdministradorEntraHac4meses_ElMetodo_antiguedad_DebeArrojar4() {

		LocalDate fecha = LocalDate.now().minusMonths(4);

		admin = new Administrador(null, null, fecha, null, null);

		assertEquals(4, admin.antiguedad());
	}
}