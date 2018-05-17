package dominio;

import dominio.dispositivos.Periodo;

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
		
		return  cargoFijoMensual + unCliente.consumoDelMesCorriente() * cargoVariable;
	}

	public double estimadoAPagar(Cliente unCliente, Periodo unPeriodo) {
		
		return  cargoFijoMensual + unCliente.consumoDe(unPeriodo) * cargoVariable;
	}
	
	public boolean leCorresponde(Cliente unCliente) {
	
		return unCliente.consumoDelMesCorriente() > consumoDesde && unCliente.consumoDelMesCorriente() <= consumoHasta;
	}
	
	public boolean leCorresponde(Cliente unCliente, Periodo unPeriodo) {
		
		return unCliente.consumoDe(unPeriodo) > consumoDesde && unCliente.consumoDe(unPeriodo) <= consumoHasta;
	}
	
	public SubtipoCategoria getSubtipoCategoria() {
		
		return subtipoCategoria;
	}
}