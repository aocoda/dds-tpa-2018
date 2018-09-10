package dominio.excepciones;

@SuppressWarnings("serial")
public class SinTransformadoresCercanosException extends RuntimeException {

	public SinTransformadoresCercanosException(String mensaje) {
		
		super(mensaje);
	}
}