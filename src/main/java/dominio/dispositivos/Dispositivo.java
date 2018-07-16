package dominio.dispositivos;

public interface Dispositivo {
	
	public double consumoDe(Periodo unPeriodo);

	public double horasDeUso(Periodo unPeriodo);

	public double getConsumoPorHora();

	public double horasDeUsoMinimo();

	public double horasDeUsoMaximo();
}