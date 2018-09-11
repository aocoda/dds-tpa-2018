package dominio.dispositivos;

public abstract class Dispositivo {
	
	protected String nombreGenerico;
	protected double consumoPorHora;
	protected double horasDeUsoMinimo;
	protected double horasDeUsoMaximo;

	public Dispositivo(String nombreGenerico, double consumoPorHora, double horasDeUsoMinimo, double horasDeUsoMaximo) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasDeUsoMinimo = horasDeUsoMinimo;
		this.horasDeUsoMaximo = horasDeUsoMaximo;
	}

	protected Dispositivo() { }

	public double horasDeUsoMinimo() {
		
		return horasDeUsoMinimo;
	}

	public double horasDeUsoMaximo() {
		
		return horasDeUsoMaximo;
	}
	
	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}
	
	public abstract double consumoDe(Periodo unPeriodo);
	
	public abstract double horasDeUso(Periodo unPeriodo);
}