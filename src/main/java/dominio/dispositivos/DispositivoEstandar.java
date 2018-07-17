package dominio.dispositivos;

public class DispositivoEstandar implements Dispositivo {

	private String nombreGenerico;
	private double consumoPorHora;
	private double horasEstimadasDeUsoPorDia;
	private double horasDeUsoMinimo;
	private double horasDeUsoMaximo;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia,
			double horasDeUsoMinimo, double horasDeUsoMaximo) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
		this.horasDeUsoMinimo = horasDeUsoMinimo;
		this.horasDeUsoMaximo = horasDeUsoMaximo;
	}
	
	@Override
	public double consumoDe(Periodo unPeriodo) {
		
		return horasDeUso(unPeriodo) * consumoPorHora;
	}
	
	@Override
	public double horasDeUso(Periodo unPeriodo) {

		return unPeriodo.cantidadDeDias() * horasEstimadasDeUsoPorDia;
	}

	@Override
	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}

	@Override
	public double horasDeUsoMinimo() {
		
		return horasDeUsoMinimo;
	}

	@Override
	public double horasDeUsoMaximo() {
		
		return horasDeUsoMaximo;
	}
}