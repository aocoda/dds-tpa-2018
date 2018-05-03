package dominio;

import java.time.LocalDate;

public class Admin {
	
	private String nombreCompleto;
	private String domicilio;
	private LocalDate fechaAltaSistema; 
	
	public Admin(String nombreCompleto, String domicilio, LocalDate fechaAltaSistema) {
		
		this.nombreCompleto = nombreCompleto;
		this.domicilio = domicilio;
		this.fechaAltaSistema = fechaAltaSistema;
	}
	
	public int antiguedad() {
		
		return fechaAltaSistema.until(LocalDate.now()).getMonths();
	}
}