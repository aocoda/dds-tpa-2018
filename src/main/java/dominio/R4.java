package dominio;

public class R4 extends Categoria {
	
	public R4() {
		
		this.cargoFijoMensual = 71.74;
		this.cargoVariable = 0.738;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
	
		return unCliente.consumoMensual() > 400 && unCliente.consumoMensual() <= 450;
	}
}