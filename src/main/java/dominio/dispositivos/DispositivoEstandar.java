package dominio.dispositivos;

import java.time.LocalDate;

public class DispositivoEstandar {

	private String nombreGenerico;
	private double consumoPorHora;
	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
	}

	//ESTO NO SE SI IRIA ASI
	public double getConsumoDelMes() {
		
		return horasEstimadasDeUsoPorDia * consumoPorHora * LocalDate.now().lengthOfMonth();
	}
}