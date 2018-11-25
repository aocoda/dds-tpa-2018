package web.viewModels;

import dominio.asesorDeUso.Recomendacion;
import dominio.dispositivos.Periodo;

public class RecomendacionVM {

	private Recomendacion recomendacion;
	private Periodo periodo;

	public RecomendacionVM(Recomendacion recomendacion, Periodo periodo) {

		this.recomendacion = recomendacion;
		this.periodo = periodo;
	}

	public long getId() {
		
		return recomendacion.getDispositivoDeInteres().getId();
	}
	
	public String getNombreGenerico() {
		
		return recomendacion.getDispositivoDeInteres().getNombreGenerico();
	}
	
	public double getHorasDeUsoRecomendadas() {
		
		return recomendacion.getHorasDeUsoRecomendadas();
	}
	
	public double getHorasDeUso() {
		
		return recomendacion.getDispositivoDeInteres().horasDeUso(periodo);
	}
	
	public double getHorasDeUsoRestantes() {
		
		return recomendacion.horasDeUsoRestantesDe(periodo);
	}
}