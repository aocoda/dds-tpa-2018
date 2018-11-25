package web.extras;

import org.apache.commons.lang3.StringUtils;

import dominio.dispositivos.Periodo;
import dominio.dispositivos.PeriodoUtils;

public class ParserPeriodos {

	public Periodo parsear(String unPeriodo) {
		
		return unPeriodo == null ? PeriodoUtils.delMesActual() : PeriodoUtils.delMesYAnio(getMesDel(unPeriodo), getAnioDel(unPeriodo));
	}
	
	
	public String parsear(Periodo unPeriodo) {
		
		String anio = String.valueOf(unPeriodo.getFechaYHoraDeInicio().getYear());
		
		String mes = StringUtils.leftPad(String.valueOf(unPeriodo.getFechaYHoraDeInicio().getMonthValue()), 2, "0");
		
		return anio + "-" + mes;
	}
	
	
	
	//Auxiliares
	private int getAnioDel(String periodo) {
		
		return Integer.parseInt(periodo.split("-")[0]);
	}
	
	private int getMesDel(String periodo) {
		
		return Integer.parseInt(periodo.split("-")[1]);
	}
}