package dominio.dispositivos;

import dominio.dispositivos.inteligentes.estados.*;

public class DispositivoInteligente {
	
	private String nombreGenerico;
	private double consumoPorHora;
	private EstadoDispositivo estadoDispositivo = new Apagado();
	
	public DispositivoInteligente(String nombreGenerico, double consumoPorHora) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
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