package dominio;

public class Categoria {
	
	private SubtipoCategoria subtipoCategoria;
	private double cargoFijoMensual;
	private double cargoVariable;
	private double consumoDesde;
	private double consumoHasta;
	
	public Categoria(SubtipoCategoria subtipoCategoria, double cargoFijoMensual, double cargoVariable,
			double consumoDesde, double consumoHasta) {
		
		this.subtipoCategoria = subtipoCategoria;
		this.cargoFijoMensual = cargoFijoMensual;
		this.cargoVariable = cargoVariable;
		this.consumoDesde = consumoDesde;
		this.consumoHasta = consumoHasta;
	}
	
	public double estimadoAPagar(Cliente unCliente) {
		
		return  cargoFijoMensual + unCliente.consumoMensual() * cargoVariable;
	}

	public boolean leCorresponde(Cliente unCliente) {
	
		return unCliente.consumoMensual() > consumoDesde && unCliente.consumoMensual() <= consumoHasta;
	}
	
	public SubtipoCategoria getSubtipoCategoria() {
		
		return subtipoCategoria;
	}
}