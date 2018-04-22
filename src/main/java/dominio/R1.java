package dominio;

public class R1 extends Categoria {
	
	public R1() {
		
		this.cargoFijoMensual = 18.76;
		this.cargoVariable = 0.644;
	}
	
	public boolean leCorresponde(Cliente unCliente) {
		
		return unCliente.consumoMensual() <= 150;
	}
}