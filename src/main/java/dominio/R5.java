package dominio;

public class R5 extends Categoria {
	
	R5() {
		
		this.cargoFijoMensual = 110.38;
		this.cargoVariable = 0.794;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumoMensual() > 450 && unCliente.consumoMensual() <= 500;
	}
}