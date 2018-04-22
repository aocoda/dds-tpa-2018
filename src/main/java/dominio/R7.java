package dominio;

public class R7 extends Categoria {
	
	public R7() {
		
		this.cargoFijoMensual = 443.59;
		this.cargoVariable = 0.851;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumoMensual() > 600 && unCliente.consumoMensual() <= 700;
	}
}