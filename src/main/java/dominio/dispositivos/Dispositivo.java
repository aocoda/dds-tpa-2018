package dominio.dispositivos;

import javax.persistence.MappedSuperclass;

import repositorios.EntidadPersistente;

@MappedSuperclass
public abstract class Dispositivo extends EntidadPersistente {
	
	protected String nombreGenerico;
	protected double consumoPorHora;
	protected double horasDeUsoMinimo;
	protected double horasDeUsoMaximo;

	public Dispositivo(String nombreGenerico, double consumoPorHora, double horasDeUsoMinimo, double horasDeUsoMaximo) {
		
		this.nombreGenerico = nombreGenerico;
		this.consumoPorHora = consumoPorHora;
		this.horasDeUsoMinimo = horasDeUsoMinimo;
		this.horasDeUsoMaximo = horasDeUsoMaximo;
	}

	protected Dispositivo() { }

	public double horasDeUsoMinimo() {
		
		return horasDeUsoMinimo;
	}

	public double horasDeUsoMaximo() {
		
		return horasDeUsoMaximo;
	}
	
	public double getConsumoPorHora() {
		
		return consumoPorHora;
	}
	
	public void setConsumoPorHora(double nuevoConsumoPorHora) {
		
		this.consumoPorHora = nuevoConsumoPorHora;
	}
	
	public abstract double consumoDe(Periodo unPeriodo);
	
	public abstract double horasDeUso(Periodo unPeriodo);
	
	public String getNombreGenerico() {
		
		return nombreGenerico;
	}

	public void setNombreGenerico(String nombreGenerico) {
		
		this.nombreGenerico = nombreGenerico;
	}
}