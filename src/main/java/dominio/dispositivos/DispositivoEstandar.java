package dominio.dispositivos;

public class DispositivoEstandar {
	
	private String nombreGenerico;
	private double consumoPorMes;
	
	public DispositivoEstandar(String nombreGenerico, double consumoPorMes) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorMes = consumoPorMes;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double getConsumoPorMes() {
		return consumoPorMes;
	}
/* no sé si este metodo debe existir y arrojar algún error o si simplemente borrarlo
	public boolean estaEncendido() {
		return estaEncendido;
	}*/
}