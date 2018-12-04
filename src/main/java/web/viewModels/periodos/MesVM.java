package web.viewModels.periodos;

import java.time.format.TextStyle;
import java.util.Locale;

import dominio.dispositivos.Periodo;
import web.extras.ParserPeriodos;

public class MesVM {

	private Periodo mes;
	private double consumo;
	
	public MesVM(Periodo mes, double consumo) {

		this.mes = mes;
		this.consumo = consumo;
	}

	public int getAnio() {
		
		return mes.getFechaYHoraDeInicio().getYear();
	}
	
	public String getMes() {
		
		return mes.getFechaYHoraDeInicio().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ARG"));
	}
	
	public double getConsumo() {
		
		return consumo;
	}
	
	public String getMesAnio() {
		
		return new ParserPeriodos().parsear(mes);
	}
}