package dominio.reglas.condiciones.comparaciones;

public class MenorA implements Relacion {

	private double unValor;

	public MenorA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor < unValor;
	}
}