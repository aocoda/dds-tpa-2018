package dominio.excepciones;

@SuppressWarnings("serial")
public class DispositivoNoAdaptadoException extends RuntimeException {

	public DispositivoNoAdaptadoException(String mensaje, Throwable causa) {
		
		super(mensaje, causa);
	}
}