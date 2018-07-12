package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

import dominio.reglas.Regla;
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
	private Collection<Regla> reglas = new HashSet<>();
	private int puntos;
	
	
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
		this.reglas = reglas;
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
	
	public double consumoDe(Periodo unPeriodo) {
		
		double consumoDispEstandar = dispositivosEstandar.stream().mapToDouble(de -> de.consumoEstimadoDe(unPeriodo)).sum();
				
		double consumoDispInteligentes = dispositivosInteligentes.stream().mapToDouble(di -> di.consumoDe(unPeriodo)).sum();
		
		return consumoDispEstandar + consumoDispInteligentes;
	}
	
	public void recategorizar(Collection<Categoria> categorias, Periodo unPeriodo) {
		
		categorias
		.stream()
		.filter(categoria -> categoria.leCorresponde(this, unPeriodo))
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
		
		sumarPuntos(10);
	}
	
	public Collection<DispositivoInteligente> getDispositivosInteligentes() {
		
		return dispositivosInteligentes;
	}
	
	public void registrarDispositivo(DispositivoInteligente dispositivo) {
		
		dispositivosInteligentes.add(dispositivo);
		
		sumarPuntos(15);
	}

	public void sumarPuntos(int puntos) {

		this.puntos  += puntos;
	}
	
	public void agregarRegla(Regla unaRegla) {
		
		reglas.add(unaRegla);
	}

	public void ejecutarReglasQueCorrespondan() {
		
		reglas.forEach(regla -> regla.ejecutarSiCorresponde());
	}

	public int getNumeroDocumento() {
		
		return numeroDocumento;
	}
}