package dominio;
import org.joda.time.DateTime;
import org.joda.time.Months;


public class Admin {
	DateTime fechaAlta; 
	String nombre;
	String apellido;
	Admin(String nombre, String apellido){
	this.nombre = nombre;
	this.apellido = apellido;
	this.fechaAlta = DateTime.now();
	}
	
	int antiguedad(){
		return 	Months.monthsBetween(fechaAlta, DateTime.now()).getMonths();
	}
}