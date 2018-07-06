package dominio.reglas.condiciones.comparaciones;

public class MayorA implements Relacion {

	private double unValor;

	public MayorA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor > unValor;
	}
}