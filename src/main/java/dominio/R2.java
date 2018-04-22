package dominio;

public class R2 extends Categoria {
	
	public R2() {
		
		this.cargoFijoMensual = 35.32;
		this.cargoVariable = 0.644;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
	
		return unCliente.consumoMensual() > 150 && unCliente.consumoMensual() <= 325;
	}
}