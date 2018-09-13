package dominio.dispositivos;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dominio.dispositivos.inteligentes.Uso;
import dominio.dispositivos.inteligentes.estados.*;

@Entity
public class DispositivoInteligente extends Dispositivo {

	@Enumerated(value = EnumType.STRING)
	private EstadoDispositivo estadoDispositivo = EstadoDispositivo.APAGADO;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "dispositivo_id")
	private Collection<Uso> historialUsos = new HashSet<Uso>();

	public DispositivoInteligente(String nombreGenerico, double consumoPorHora, double horasDeUsoMinimo, double horasDeUsoMaximo) {

		super(nombreGenerico, consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo);
	}
	
	@SuppressWarnings("unused")
	private DispositivoInteligente() {
		
		estadoDispositivo = EstadoDispositivo.APAGADO;
		historialUsos = new HashSet<Uso>();
	}
	
	public Collection<Uso> usosDe(Periodo unPeriodo) {
		
		return historialUsos
				.stream()
				.filter(uso -> unPeriodo.contiene(uso.getPeriodo()))
				.map(uso -> uso.acotarExtremos(unPeriodo))
				.collect(Collectors.toSet());
	}
	
	@Override
	public double consumoDe(Periodo unPeriodo) {

		return usosDe(unPeriodo)
				.stream()
				.mapToDouble(uso -> uso.consumo(consumoPorHora))
				.sum();
	}
	
	@Override
	public double horasDeUso(Periodo unPeriodo) {

		return usosDe(unPeriodo)
				.stream()
				.mapToDouble(uso -> uso.getPeriodo().cantidadDeHoras())
				.sum();
	}

	public void agregarUso(Periodo unPeriodo) {
		
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
}