package web.extras;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(String mensaje) {
		
		super(mensaje);
	}
}