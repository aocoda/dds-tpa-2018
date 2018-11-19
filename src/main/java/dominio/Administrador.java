package dominio;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import dominio.autenticacion.Usuario;

@Entity
public class Administrador extends Usuario {
	
	private String nombreCompleto;
	private String domicilio;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fechaAltaSistema; 
	
	public Administrador(String nombreCompleto, String domicilio, LocalDate fechaAltaSistema, String nombreUsuario, String contrasenia) {
		
		super(nombreUsuario, contrasenia);
		
		this.nombreCompleto = nombreCompleto;
		this.domicilio = domicilio;
		this.fechaAltaSistema = fechaAltaSistema;
	}
	
	public int antiguedad() {
		
		return fechaAltaSistema.until(LocalDate.now()).getMonths();
	}

	@Override
	public boolean esAdmin() {
		
		return true;
	}
}