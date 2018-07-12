package dominio.dispositivos;

public class DispositivoEstandar implements Dispositivo {

	private String nombreGenerico;
	private double consumoPorHora;
	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
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
}