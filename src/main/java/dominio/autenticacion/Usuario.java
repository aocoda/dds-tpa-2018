package dominio.autenticacion;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.mindrot.jbcrypt.BCrypt;

import repositorios.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario extends EntidadPersistente {

	private String nombreUsuario;
	private String contraseniaHasheada;
	private String salt;
	
	public Usuario(String nombreUsuario, String contrasenia) {
		
		this.nombreUsuario = nombreUsuario;
		this.salt = BCrypt.gensalt();
		this.contraseniaHasheada = BCrypt.hashpw(contrasenia, salt);
	}
	
	protected Usuario() { }

	public boolean esContraseniaValida(String posibleContrasenia) {
		
		return BCrypt.checkpw(posibleContrasenia, contraseniaHasheada);
	}
	
	public String getNombreUsuario() {
		
		return nombreUsuario;
	}
	
	public String getContraseniaHasheada() {
		
		return contraseniaHasheada;
	}
	
	public String getSalt() {
		
		return salt;
	}
	
	public abstract boolean esAdmin();
}