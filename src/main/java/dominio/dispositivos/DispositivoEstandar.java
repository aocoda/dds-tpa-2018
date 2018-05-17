package dominio.dispositivos;

public class DispositivoEstandar {

	private String nombreGenerico;
	private double consumoPorHora;
	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
	}
	
	public double consumoEstimadoDe(Periodo unPeriodo) {
		
		return unPeriodo.cantidadDeDias() * horasEstimadasDeUsoPorDia * consumoPorHora;
	}
}