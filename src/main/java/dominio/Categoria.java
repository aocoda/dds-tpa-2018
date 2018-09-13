package dominio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import dominio.dispositivos.Periodo;
import repositorios.EntidadPersistente;

@Entity
public class Categoria extends EntidadPersistente {
	
	@Enumerated(value = EnumType.STRING)
	private TipoCategoria tipoCategoria;
	private double cargoFijoMensual;
	private double cargoVariable;
	private double consumoDesde;
	private double consumoHasta;
	
	public Categoria(TipoCategoria tipoCategoria, double cargoFijoMensual, double cargoVariable,
			double consumoDesde, double consumoHasta) {
		
		this.tipoCategoria = tipoCategoria;
		this.cargoFijoMensual = cargoFijoMensual;
		this.cargoVariable = cargoVariable;
		this.consumoDesde = consumoDesde;
		this.consumoHasta = consumoHasta;
	}
	
	@SuppressWarnings("unused")
	private Categoria() { }
	
	public double estimadoAPagar(Cliente unCliente, Periodo unPeriodo) {
		
		return  cargoFijoMensual + unCliente.consumoDe(unPeriodo) * cargoVariable;
	}
	
	public boolean leCorresponde(Cliente unCliente, Periodo unPeriodo) {
		
		return unCliente.consumoDe(unPeriodo) > consumoDesde && unCliente.consumoDe(unPeriodo) <= consumoHasta;
	}
	
	public TipoCategoria getSubtipoCategoria() {
		
		return tipoCategoria;
	}
}