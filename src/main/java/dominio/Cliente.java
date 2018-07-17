package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dominio.reglas.Actuador;
import dominio.reglas.Regla;
import dominio.reglas.condiciones.CondicionSimplex;
import dominio.asesorDeUso.AsesorDeUso;
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
	private boolean apagadoAutomaticoActivado;
	
	
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
	
	public double consumoDe(Periodo unPeriodo) {
		
		return getDispositivos()
				.stream()
				.mapToDouble(dispositivo -> dispositivo.consumoDe(unPeriodo))
				.sum();
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
	
	public void ejecutarApagadoAutomaticoPorConsumo(Periodo unPeriodo) {
		
		Actuador actuador = unDispositivo -> unDispositivo.apagar();
		
		AsesorDeUso asesor = new AsesorDeUso();
		
		List<Dispositivo> dispositivos = getDispositivos().stream().collect(Collectors.toList());
		
		if(apagadoAutomaticoActivado)
			
			dispositivosInteligentes
			.stream()
			.map(dispositivo -> {
				
				CondicionSimplex condicion = new CondicionSimplex(asesor, dispositivo, dispositivos, unPeriodo);
				
				return new Regla(condicion, actuador, Collections.singletonList(dispositivo));
			})
			.forEach(reglaSimplex -> reglaSimplex.ejecutarSiCorresponde());
	}

	public Collection<Dispositivo> getDispositivos() {
		
		return Stream
				.concat(dispositivosEstandar.stream(), dispositivosInteligentes.stream())
				.collect(Collectors.toSet());
	}
}