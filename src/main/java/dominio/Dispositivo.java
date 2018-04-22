package dominio;

public class Dispositivo {
	
	private String nombreGenerico;
	private double consumoPorMes;
	private boolean estaEncendido;
	
	public Dispositivo(String nombreGenerico, double consumoPorMes, boolean estaEncendido) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorMes = consumoPorMes;
		this.estaEncendido = estaEncendido;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double getConsumoPorMes() {
		return consumoPorMes;
	}

	public boolean estaEncendido() {
		return estaEncendido;
	}
}