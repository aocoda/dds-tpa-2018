package dominio.reglas.condiciones.comparaciones;

public class IgualA implements Relacion {

	private double unValor;

	public IgualA(double unValor) {
		
		this.unValor = unValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return valor == unValor;
	}
}