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

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double getConsumoPorHora() {
		return consumoPorHora;
	}

	public double horasEstimadasDeUsoPorDia() {
		return horasEstimadasDeUsoPorDia;
	}

	public double getConsumoDelMes() {
		return horasEstimadasDeUsoPorDia * consumoPorHora * LocalDate.now().lengthOfMonth();
	}
}