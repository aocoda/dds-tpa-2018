package dominio.dispositivos;

import java.util.Collection;

import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.*;

public class DispositivoInteligente {
	
	private String nombreGenerico;
	private double consumoPorHora;
	private EstadoDispositivo estadoDispositivo = new Apagado();
	
	private Collection<Uso> historialUsos;
	
	public DispositivoInteligente(String nombreGenerico, double consumoPorHora) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
	}

	public double consumoDe(Periodo unPeriodo) {
		
		return historialUsos
				.stream()
				.filter(uso -> uso.getPeriodo().contiene(unPeriodo))
				.map(uso -> uso.acotarExtremos(historialUsos, unPeriodo))
				.mapToDouble(uso -> uso.consumo(consumoPorHora))
				.sum();
	}
	
	public double consumoDeLasUltimas(double nHoras) {
		
		Periodo unPeriodo = Periodo.deLasUltimas(nHoras);
		
		return consumoDe(unPeriodo);
	}
	
	public void cambiarEstado(EstadoDispositivo nuevoEstado) {
		
		this.estadoDispositivo = nuevoEstado;
	}

	public void encender() {
		
		estadoDispositivo.encender(this);
	}

	public void apagar() {
		
		estadoDispositivo.apagar(this);
	}

	public void modoAhorro() {
		
		estadoDispositivo.modoAhorro(this);
	}

	public boolean estaEncendido() {
		
		return estadoDispositivo.estaEncendido();
	}
	
	public boolean estaApagado() {
		
		return estadoDispositivo.estaApagado();
	}
}