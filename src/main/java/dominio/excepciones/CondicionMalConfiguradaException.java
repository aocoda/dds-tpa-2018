package dominio.excepciones;

@SuppressWarnings("serial")
public class CondicionMalConfiguradaException extends RuntimeException {

	public CondicionMalConfiguradaException(String mensaje) {
		
		super(mensaje);
	}
}