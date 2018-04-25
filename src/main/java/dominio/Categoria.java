package dominio;

public abstract class Categoria {
	
	protected double cargoFijoMensual;
	protected double cargoVariable;

	public double estimadoAPagar(Cliente unCliente) {
		
		return  cargoFijoMensual + unCliente.consumoMensual() * cargoVariable;
	}

	abstract boolean leCorresponde(Cliente unCliente);
}