package dominio;

public class R6 extends Categoria {
	
	public R6() {
		
		this.cargoFijoMensual = 220.75;
		this.cargoVariable = 0.832;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumoMensual() > 500 && unCliente.consumoMensual() <= 600;
	}
}