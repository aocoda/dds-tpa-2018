package dominio;

public class R8 extends Categoria {
	
	public R8() {
		
		this.cargoFijoMensual = 545.96;
		this.cargoVariable = 0.851;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumoMensual() > 700 && unCliente.consumoMensual() <= 1400;
	}
}