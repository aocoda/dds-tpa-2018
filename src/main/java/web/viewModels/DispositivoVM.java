package web.viewModels;

import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.Periodo;

public class DispositivoVM {

	private Dispositivo dispositivo;
	private Periodo periodo;
	
	public DispositivoVM(Dispositivo dispositivo, Periodo periodo) {
		
		this.dispositivo = dispositivo;
		this.periodo = periodo;
	}
	
	public long getId() {
		
		return dispositivo.getId();
	}
	
	public String getNombreGenerico() {
		
		return dispositivo.getNombreGenerico();
	}
	
	public double getConsumoPorHora() {
		
		return dispositivo.getConsumoPorHora();
	}
	
	public double getConsumo() {
		
		return dispositivo.consumoDe(periodo);
	}
	
	public double getHorasDeUso() {
		
		return dispositivo.horasDeUso(periodo);
	}
	
	public Dispositivo getDispositivo() {
		
		return dispositivo;
	}
}