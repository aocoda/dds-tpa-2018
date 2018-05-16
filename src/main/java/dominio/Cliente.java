package dominio;

import java.time.LocalDate;
import java.util.Collection;
import dominio.dispositivos.*;
import dominio.dispositivos.adaptadores.*;

public class Cliente {
	
	private String nombreCompleto;
	private TipoDocumento tipoDocumento;
	private int numeroDocumento;
	private String telefono;
	private String domicilio;
	private LocalDate fechaAltaServicio;
	private Categoria categoria;
	private Collection<DispositivoEstandar> dispositivosEstandar;
	private Collection<DispositivoInteligente> dispositivosInteligentes;
	
	
	
	public Cliente(String nombreCompleto, TipoDocumento tipoDocumento, int numeroDocumento, String telefono,
			String domicilio, LocalDate fechaAltaServicio, Categoria categoria,
			Collection<DispositivoEstandar> dispositivosEstandar,
			Collection<DispositivoInteligente> dispositivosInteligentes) {

		this.nombreCompleto = nombreCompleto;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivosEstandar = dispositivosEstandar;
		this.dispositivosInteligentes = dispositivosInteligentes;
	}

	public boolean existeDispositivoEncendido() {
		
		return dispositivosInteligentes
				.stream()
				.anyMatch(dispositivo -> dispositivo.estaEncendido());
	}
	
	public long cantidadDispositivosEncendidos() {
		
		return dispositivosInteligentes
				.stream()
				.filter(dispositivo -> dispositivo.estaEncendido())
				.count();
	}
	
	public long cantidadDispositivosApagados() {
		
		return dispositivosInteligentes
				.stream()
				.filter(dispositivo -> dispositivo.estaApagado())
				.count();
	}
	
	public long cantidadDispositivos() {
		
		return dispositivosInteligentes.size() + dispositivosEstandar.size();
	}
	
	public double consumoMensual() {
		
		return this.consumoMensualDeDispositivosEstandar() + this.consumoMensualDeDispositivosInteligentes();
	}
	
	private double consumoMensualDeDispositivosEstandar() {
		
		return dispositivosEstandar
				.stream()
				.mapToDouble(dispositivo -> dispositivo.getConsumoDelMes())
				.sum();
	}
	
	private double consumoMensualDeDispositivosInteligentes() {
/*		return dispositivosInteligentes
				.stream()
				.mapToDouble(dispositivo -> dispositivo.getConsumo())
				.sum();*/ return 0;
	}
	
	public void recategorizar(Collection<Categoria> categorias) {
		
		categorias
		.stream()
		.filter(categoria -> categoria.leCorresponde(this))
		.findFirst()
		.ifPresent(categoria -> this.categoria = categoria);
	}

	public Categoria getCategoria() {
		
		return categoria;
	}
	
	public void transformar(DispositivoEstandar dispositivoEstandar, ModuloAdaptador adaptador) {
		
		DispositivoInteligente dispositivoInteligente = adaptador.adaptar(dispositivoEstandar);
		
		dispositivosEstandar.remove(dispositivoEstandar);
		
		dispositivosInteligentes.add(dispositivoInteligente);
	}
}