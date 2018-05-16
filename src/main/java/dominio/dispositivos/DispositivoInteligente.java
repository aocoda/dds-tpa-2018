package dominio.dispositivos;

import dominio.dispositivos.estados.*;

public class DispositivoInteligente {
	
	private String nombreGenerico;
	private double consumoPorHora;
	private EstadoDispositivo estadoDispositivo;
	
	public DispositivoInteligente(String nombreGenerico, double consumoPorHora, EstadoDispositivo estadoDispositivo) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.estadoDispositivo = estadoDispositivo;
	}

	public String getNombreGenerico() {
		
		return nombreGenerico;
	}

	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}
	
	public EstadoDispositivo getModo() {
		
		return estadoDispositivo;
	}

/*	public double getConsumo() {
		// ...hace lo suyo...
	}*/

	public void cambiarEstado(EstadoDispositivo nuevoModo) {
		
		this.estadoDispositivo = nuevoModo;
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