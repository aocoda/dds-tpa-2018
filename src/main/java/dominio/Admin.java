package dominio;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


public class Admin {
	
	private LocalDate fechaAltaSistema; 
	private String nombreCompleto;
	private String apellido;
	private String domicilio;
	
	public Admin(String nombre, String apellido, String domicilio, LocalDate fecha){
		this.nombreCompleto = nombre;
		this.apellido = apellido;
		this.domicilio = nombre;
		this.fechaAltaSistema = fecha;
	}
	
	public int antiguedad(){
		return fechaAltaSistema.until(LocalDate.now()).getMonths();
	}
}