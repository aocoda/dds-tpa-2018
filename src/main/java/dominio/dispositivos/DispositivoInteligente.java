package dominio.dispositivos;

import dominio.dispositivos.estados.*;

public class DispositivoInteligente {
	
	private String nombreGenerico;
	private double consumoPorHora;
	private EstadoDispositivo modoDeOperacion;
	
	public DispositivoInteligente(String nombreGenerico, double consumoPorHora, EstadoDispositivo nuevoModo) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.modoDeOperacion = nuevoModo;
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double getConsumoPorHora() {
		return consumoPorHora;
	}
	
	public EstadoDispositivo getModo() {
		return modoDeOperacion;
	}

/*	public double getConsumo() {
		// ...hace lo suyo...
	}*/

	public void cambiarEstado(EstadoDispositivo nuevoModo) {
		this.modoDeOperacion = nuevoModo;
	}

	public void encender() {
		modoDeOperacion.encender(this);
	}

	public void apagar() {
		modoDeOperacion.apagar(this);
	}

	public void modoAhorro() {
		modoDeOperacion.modoAhorro(this);
	}

	public boolean estaEncendido() {
		return modoDeOperacion.estaEncendido();
	}
	
	public boolean estaApagado() {
		return modoDeOperacion.estaApagado();
	}
}