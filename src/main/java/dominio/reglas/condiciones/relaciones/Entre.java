package dominio.reglas.condiciones.relaciones;

public class Entre implements Relacion {

	private double unValor;
	private double otroValor;
	
	public Entre(double unValor, double otroValor) {
		
		this.unValor = unValor;
		this.otroValor = otroValor;
	}

	@Override
	public boolean aplicarCon(double valor) {
		
		return unValor < valor && valor < otroValor;
	}
}