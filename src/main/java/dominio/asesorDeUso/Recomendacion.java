package dominio.asesorDeUso;

import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.Periodo;

public class Recomendacion {

	private Dispositivo dispositivoDeInteres;
	private double horasDeUsoRecomendadas;

	public Recomendacion(Dispositivo dispositivoDeInteres, double horasDeUsoRecomendadas) {
		
		this.dispositivoDeInteres = dispositivoDeInteres;
		this.horasDeUsoRecomendadas = horasDeUsoRecomendadas;
	}
	
	public Dispositivo getDispositivoDeInteres() {
	
		return dispositivoDeInteres;
	}
	
	public double getHorasDeUsoRecomendadas() {
		
		return horasDeUsoRecomendadas;
	}
	
	public double horasDeUsoRestantesDe(Periodo unPeriodo) {
		
		return horasDeUsoRecomendadas - dispositivoDeInteres.horasDeUso(unPeriodo);
	}
}