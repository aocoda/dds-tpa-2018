package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

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
				.reduce(0d, (a,d) -> a + d.getConsumoPorMes(), Double::sum);
	}
	
	public void recategorizar() {
		
		RepositorioCategorias.getInstancia().getAllInstances().forEach(categoria -> {
			
			if(categoria.leCorresponde(this))
				
				this.categoria = categoria;
		});
	}

	public Categoria getCategoria() {
		
		return categoria;
	}
}