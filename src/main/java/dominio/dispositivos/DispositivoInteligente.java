package dominio.dispositivos;

import java.util.Collection;

public class DispositivoInteligente {

	private Collection<Consumo> historialConsumo;
	private double consumoPorHora;
	private EstadoDispositivo modoDeOperacion = new Apagado(); // lo inicie en apagado

	public double getConsumo(Periodo unPeriodo) {
// ... hace lo suyo ...
	}

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

}