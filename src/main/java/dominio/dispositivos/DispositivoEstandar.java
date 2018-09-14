package dominio.dispositivos;

import javax.persistence.Entity;

@Entity
public class DispositivoEstandar extends Dispositivo {

	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia,
			double horasDeUsoMinimo, double horasDeUsoMaximo) {

		super(nombreGenerico, consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo);
		
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
	
	public DispositivoInteligente adaptar() {
		
		return new DispositivoInteligente(nombreGenerico, consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo);
	}
}