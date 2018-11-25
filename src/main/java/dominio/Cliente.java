package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import dominio.asesorDeUso.AsesorDeUso;
import dominio.autenticacion.Usuario;
import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.excepciones.SinTransformadoresCercanosException;
import dominio.geoposicionamiento.Coordenada;
import dominio.reglas.Regla;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.actuadores.Apagador;
import dominio.reglas.condiciones.CondicionSimplex;

@Entity
public class Cliente extends Usuario {
	
	private String nombreCompleto;
	@Enumerated(value = EnumType.STRING)
	private TipoDocumento tipoDocumento;
	private int numeroDocumento;
	private String telefono;
	private String domicilio;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fechaAltaServicio;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<DispositivoEstandar> dispositivosEstandar;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<DispositivoInteligente> dispositivosInteligentes;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private List<Regla> reglas = new ArrayList<>();
	private int puntos;
	private boolean apagadoAutomaticoActivado;
	@Embedded
	private Coordenada coordenada;
	
	
	public Cliente(String nombreCompleto, TipoDocumento tipoDocumento, int numeroDocumento, String telefono,
			String domicilio, LocalDate fechaAltaServicio, Categoria categoria,
			List<DispositivoEstandar> dispositivosEstandar,
			List<DispositivoInteligente> dispositivosInteligentes,
			Coordenada coordenada, String nombreUsuario, String contrasenia) {
		
		super(nombreUsuario, contrasenia);

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
		
		dispositivosEstandar = new ArrayList<>();
		dispositivosInteligentes = new ArrayList<>();
		reglas = new ArrayList<>();
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
	
	public void recategorizar(List<Categoria> categorias, Periodo unPeriodo) {
		
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
	
	public List<DispositivoInteligente> getDispositivosInteligentes() {
		
		return dispositivosInteligentes;
	}
	
	public void registrarDispositivo(DispositivoInteligente dispositivo) {
		
		dispositivosInteligentes.add(dispositivo);
		
		sumarPuntos(15);
	}
	
	public void registrarDispositivo(DispositivoEstandar dispositivo) {
		
		dispositivosEstandar.add(dispositivo);
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
		
		Actuador actuador = new Apagador();
		
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

	public List<Dispositivo> getDispositivos() {
		
		return Stream
				.concat(dispositivosEstandar.stream(), dispositivosInteligentes.stream())
				.collect(Collectors.toList());
	}

	public boolean tieneApagadoAutomaticoActivado() {
		
		return apagadoAutomaticoActivado;
	}

	public Transformador transformadorAsociado(List<Transformador> transformadores) {
		
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
	
	public List<DispositivoEstandar> getDispositivosEstandar() {
		
		return dispositivosEstandar;
	}

	public Coordenada getCoordenada() {
		
		return coordenada;
	}
	
	public List<Regla> getReglas() {
		
		return reglas;
	}

	public void setCoordenada(Coordenada coordenada) {
		
		this.coordenada = coordenada;
	}

	@Override
	public boolean esAdministrador() {
		
		return false;
	}

	public String getNombreCompleto() {
		
		return nombreCompleto;
	}

	public boolean tieneHogarEficiente(Periodo unPeriodo) {
		
		return consumoDe(unPeriodo) < 612;
	}
}