package dominio.excepciones;

@SuppressWarnings("serial")
public class RecursoInvalidoException extends RuntimeException {

	public RecursoInvalidoException(String mensaje, Throwable causa) {
		
		super(mensaje, causa);
	}
}