package dominio.dispositivos;

import java.util.Collection;

public class DispositivoInteligente {

	private Collection<Consumo> historialConsumo;
	private double consumoPorHora;
	private EstadoDispositivo modoDeOperacion; // el nombre puede cambiar

	public double getConsumo(Periodo unPeriodo) {

		return historialConsumo.stream().filter(consumo -> unPeriodo.contiene(consumo.getPeriodo()))
				.mapToDouble(consumo -> consumo.getConsumo(consumoPorHora)).sum();
	}

	public double getConsumo(double nHoras) {

		int contador = 0;

		// ordenar las fechas del historial primero

		for (Consumo consumo : historialConsumo) {

			double cantidadHorasEncendido = consumo.getPeriodo().cantidadDeHoras();

			if (cantidadHorasEncendido < nHoras)
				contador++;
		}

		return historialConsumo.stream().limit(contador).mapToDouble(consumo -> consumo.getConsumo(consumoPorHora))
				.sum();
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

}