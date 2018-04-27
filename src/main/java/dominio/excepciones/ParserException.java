package dominio.excepciones;

@SuppressWarnings("serial")
public class ParserException extends RuntimeException {

	public ParserException(String mensaje, Throwable causa) {
		
		super(mensaje, causa);
	}
}