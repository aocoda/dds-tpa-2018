package dominio;

public class R3 extends Categoria {
	
	public R3() {
		
		this.cargoFijoMensual = 60.71;
		this.cargoVariable= 0.681;
	}
		
	public boolean leCorresponde(Cliente unCliente) {
	
		return unCliente.consumoMensual() > 325 && unCliente.consumoMensual() <= 400;
	}
}