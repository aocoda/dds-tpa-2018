package dominio.dispositivos;

import java.util.HashSet;

import javax.persistence.Entity;

import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;

@Entity
public class DispositivoEstandar extends Dispositivo {

	private double horasEstimadasDeUsoPorDia;

	public DispositivoEstandar(String nombreGenerico, double consumoPorHora, double horasEstimadasDeUsoPorDia,
			double horasDeUsoMinimo, double horasDeUsoMaximo) {

		super(nombreGenerico, consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo);
		
		this.horasEstimadasDeUsoPorDia = horasEstimadasDeUsoPorDia;
	}
	
	@SuppressWarnings("unused")
	private DispositivoEstandar() { }
	
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