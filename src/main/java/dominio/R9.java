package dominio;

public class R9 extends Categoria {
	
	public R9 () {
		
		this.cargoFijoMensual = 887.19;
		this.cargoVariable = 0.851;
	}
	
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumo() > 1400;
	}
}