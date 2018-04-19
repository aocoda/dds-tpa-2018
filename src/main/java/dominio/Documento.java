package dominio;

public class Documento {
	
	private TipoDocumento tipo;
	private int numero;
	
	public Documento(TipoDocumento tipo, int numero) {
		
		this.tipo = tipo;
		this.numero = numero;
	}

	public TipoDocumento getTipo() {
		return tipo;
	}

	public int getNumero() {
		return numero;
	}	
}