package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import dominio.reglas.Actuador;
import dominio.reglas.Regla;
import dominio.reglas.condiciones.CondicionSimplex;
import repositorios.EntidadPersistente;
import dominio.asesorDeUso.AsesorDeUso;
import dominio.dispositivos.*;
import dominio.excepciones.SinTransformadoresCercanosException;
import dominio.geoposicionamiento.Coordenada;

@Entity
public class Cliente extends EntidadPersistente {
	
	private String nombreCompleto;
	@Enumerated(value = EnumType.STRING)
	private TipoDocumento tipoDocumento;
	private int numeroDocumento;
	private String telefono;
	private String domicilio;
	private LocalDate fechaAltaServicio;
	@ManyToOne
	private Categoria categoria;
	@OneToMany
	@JoinColumn(name = "cliente_id")
	private Collection<DispositivoEstandar> dispositivosEstandar;
	@OneToMany
	@JoinColumn(name = "cliente_id")
	private Collection<DispositivoInteligente> dispositivosInteligentes;
	//@OneToMany
		@Transient
	private Collection<Regla> reglas = new HashSet<>();
	private int puntos;
	private boolean apagadoAutomaticoActivado;
	@Embedded
	private Coordenada coordenada;
	
	
	public Cliente(String nombreCompleto, TipoDocumento tipoDocumento, int numeroDocumento, String telefono,
			String domicilio, LocalDate fechaAltaServicio, Categoria categoria,
			Collection<DispositivoEstandar> dispositivosEstandar,
			Collection<DispositivoInteligente> dispositivosInteligentes,
			Coordenada coordenada) {

		this.nombreCompleto = nombreCompleto;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivosEstandar = dispositivosEstandar;
		this.dispositivosInteligentes = dispositivosInteligentes;
		this.coordenada = coordenada;
	}
	
	@SuppressWarnings("unused")
	private Cliente() {
		
		dispositivosEstandar = new HashSet<>();
		dispositivosInteligentes = new HashSet<>();
		reglas = new HashSet<>();
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
	
	public void transformar(DispositivoEstandar dispositivoEstandar) {
		
		DispositivoInteligente dispositivoInteligente = dispositivoEstandar.adaptar();
		
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
	
	public void ejecutarApagadoPorConsumo(Periodo unPeriodo) {
		
		Actuador actuador = unDispositivo -> unDispositivo.apagar();
		
		AsesorDeUso asesor = new AsesorDeUso();
		
		List<Dispositivo> dispositivos = getDispositivos().stream().collect(Collectors.toList());
			
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

	public boolean tieneApagadoAutomaticoActivado() {
		
		return apagadoAutomaticoActivado;
	}

	public Transformador transformadorAsociado(Collection<Transformador> transformadores) {
		
		return transformadores
				.stream()
				.min(Comparator.comparing(transformador -> transformador.getCoordenada().distanciaA(coordenada)))
				.orElseThrow(() -> new SinTransformadoresCercanosException("No se pudo asociar ningun transformador"));
	}
	
	public TipoDocumento getTipoDocumento() {
		
		return tipoDocumento;
	}

	public LocalDate getFechaAltaServicio() {
		
		return fechaAltaServicio;
	}
	
	public Collection<DispositivoEstandar> getDispositivosEstandar() {
		
		return dispositivosEstandar;
	}

	public Coordenada getCoordenada() {
		
		return coordenada;
	}
	
	public Collection<Regla> getReglas() {
		
		return reglas;
	}
}