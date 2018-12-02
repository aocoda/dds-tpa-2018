package dominio.autenticacion;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Administrador;

public class UsuarioTest {

	@Test
	public void AlCrearUnUsuarioLaContraseniaDebeGuardarseHasheada() {
		
		String contrasenia = "abc123";
		
		Usuario usuario = new Administrador(null, null, null, "usuario", contrasenia);
		
		String contraseniaHasheada = usuario.getContraseniaHasheada();
		
		assertFalse(contrasenia.equals(contraseniaHasheada));
	}
	
	@Test
	public void DosUsuarioDistintosNoDebenTenerElMismoSalt() {
		
		Usuario usuario1 = new Administrador(null, null, null, "u1", "abc123");
		Usuario usuario2 = new Administrador(null, null, null, "u2", "123abc");
		
		assertFalse(usuario1.getSalt().equals(usuario2.getSalt()));
	}
	
	@Test
	public void DosUsuarioDistintosNoDebenTenerLaMismaContraseniaHasheadaAunqueTenganLaMismaContrasenia() {
		
		String contrasenia = "abc123";
		
		Usuario usuario1 = new Administrador(null, null, null, "u1", contrasenia);
		Usuario usuario2 = new Administrador(null, null, null, "u2", contrasenia);
		
		assertFalse(usuario1.getContraseniaHasheada().equals(usuario2.getContraseniaHasheada()));
	}
	
	@Test
	public void UnUsuarioCreadoConUnaContraseniaDebeTenerEsaContraseniaComoValida() {
		
		String contrasenia = "abc123";
		
		Usuario usuario = new Administrador(null, null, null, "usuario", contrasenia);
		
		assertTrue(usuario.esContraseniaValida(contrasenia));
	}
	
	@Test
	public void DosUsuarioDistintosCreadosConLaMismaContraseniaDebenTenerEsaContraseniaComoValidaYDistintaContraseniaHasheada() {
		
		String contrasenia = "abc123";
		
		Usuario usuario1 = new Administrador(null, null, null, "u1", contrasenia);
		Usuario usuario2 = new Administrador(null, null, null, "u2", contrasenia);
		
		assertTrue(usuario1.esContraseniaValida(contrasenia) && usuario2.esContraseniaValida(contrasenia));
		assertFalse(usuario1.getContraseniaHasheada().equals(usuario2.getContraseniaHasheada()));
	}
}