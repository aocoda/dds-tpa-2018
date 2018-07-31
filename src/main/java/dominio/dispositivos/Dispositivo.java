package dominio.dispositivos;

public abstract class Dispositivo {
	
	private double consumoPorHora;
	private double horasDeUsoMinimo;
	private double horasDeUsoMaximo;

	public Dispositivo(double consumoPorHora, double horasDeUsoMinimo, double horasDeUsoMaximo) {
		
		this.consumoPorHora = consumoPorHora;
		this.horasDeUsoMinimo = horasDeUsoMinimo;
		this.horasDeUsoMaximo = horasDeUsoMaximo;
	}
	
	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}

	public double horasDeUsoMinimo() {
		
		return horasDeUsoMinimo;
	}

	public double horasDeUsoMaximo() {
		
		return horasDeUsoMaximo;
	}
	
	public abstract double consumoDe(Periodo unPeriodo);
	
	public abstract double horasDeUso(Periodo unPeriodo);
}