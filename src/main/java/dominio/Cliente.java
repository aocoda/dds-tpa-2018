package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import repositorios.RepositorioCategorias;

public class Cliente {
	
	private String nombreCompleto;
	private String apellido;
	private Documento documento;
	private String telefono;
	private String domicilio;
	private LocalDate fechaAltaServicio;
	private Categoria categoria;
	private Collection<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	
	
	public Cliente(String nombre, Documento documento, String telefono, String domicilio,
			LocalDate fechaAltaServicio, Categoria categoria, Collection<Dispositivo> dispositivos) {
		
		this.nombreCompleto = nombre;
		this.documento = documento;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
	}

	public boolean existeDispositivoEncendido() {
		
		return dispositivos
				.stream()
				.anyMatch(dispositivo -> dispositivo.estaEncendido());
	}
	
	public long cantidadDispositivosEncendidos() {
		
		return dispositivos
				.stream()
				.filter(dispositivo -> dispositivo.estaEncendido())
				.count();
	}
	
	public long cantidadDispositivosApagados() {
		
		return dispositivos
				.stream()
				.filter(dispositivo -> !dispositivo.estaEncendido())
				.count();
	}
	
	public long cantidadDispositivos() {
		
		return dispositivos.size();
	}
	
	public double consumoMensual() {
		
		return dispositivos
				.stream()
				.mapToDouble(dispositivo -> dispositivo.getConsumoPorMes())
				.sum();
	}
	
	public void recategorizar(Set<Categoria> categorias) {
		
		categorias
		.stream()
		.filter(categoria -> categoria.leCorresponde(this))
		.findFirst()
		.ifPresent(categoria -> this.categoria = categoria);
	}

	public Categoria getCategoria() {
		
		return categoria;
	}
}