package dominio;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class Cliente {
	
	private String nombreCompleto;
	private String apellido;
	private Documento documento;
	private String telefono;
	private String domicilio;
	private LocalDate fechaAltaServicio;
	//private Categoria categoria;
	private Collection<Dispositivo> dispositivos = new HashSet<Dispositivo>();
	
	
	public Cliente(String nombre, Documento documento, String telefono, String domicilio,
			LocalDate fechaAltaServicio, Collection<Dispositivo> dispositivos) {
		
		this.nombreCompleto = nombre;
		this.documento = documento;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.fechaAltaServicio = fechaAltaServicio;
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
}