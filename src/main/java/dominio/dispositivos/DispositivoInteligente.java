package dominio.dispositivos;

import java.util.Collection;
import java.util.HashSet;

import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.*;

public abstract class DispositivoInteligente {

	private String nombreGenerico;
	private double consumoPorHora;
	private EstadoDispositivo estadoDispositivo = new Apagado();

	private Collection<Uso> historialUsos = new HashSet<Uso>();

	public DispositivoInteligente(String nombreGenerico, double consumoPorHora) {

		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
	}

	public double consumoDe(Periodo unPeriodo) {

		return historialUsos
				.stream()
				.filter(uso -> unPeriodo.contiene(uso.getPeriodo()))
				.map(uso -> uso.acotarExtremos(unPeriodo))
				.mapToDouble(uso -> uso.consumo(consumoPorHora))
				.sum();
	}
	
	public double horasDeUso(Periodo unPeriodo) {

		return historialUsos
				.stream()
				.filter(uso -> unPeriodo.contiene(uso.getPeriodo()))
				.map(uso -> uso.acotarExtremos(unPeriodo))
				.mapToDouble(uso -> uso.getPeriodo().cantidadDeHoras())
				.sum();
	}

	public void addUso(Periodo unPeriodo) {
		
		historialUsos.add(new Uso(unPeriodo, estadoDispositivo));
	}

	public double consumoDeLasUltimas(double nHoras) {

		Periodo unPeriodo = Periodo.deLasUltimasNHoras(nHoras);

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
	
	public EstadoDispositivo getEstadoActual() {
		
		return estadoDispositivo;
	}

	public Collection<Uso> getHistorialUsos() {
		
		return historialUsos;
	}

	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}
}