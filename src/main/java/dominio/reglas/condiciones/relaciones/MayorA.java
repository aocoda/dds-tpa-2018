package dominio.reglas.condiciones.relaciones;

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