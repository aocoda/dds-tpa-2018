package dominio.dispositivos;

public class DispositivoEstandar extends Dispositivo {

	private String nombreGenerico;
	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia,
			double horasDeUsoMinimo, double horasDeUsoMaximo) {

		super(consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo);
		
		this.nombreGenerico = nombreGenerico;
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
	}
	
	@Override
	public double consumoDe(Periodo unPeriodo) {
		
		return horasDeUso(unPeriodo) * getConsumoPorHora();
	}
	
	@Override
	public double horasDeUso(Periodo unPeriodo) {

		return unPeriodo.cantidadDeDias() * horasEstimadasDeUsoPorDia;
	}
}