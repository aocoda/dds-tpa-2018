package dominio.reglas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.condiciones.Condicion;
import repositorios.EntidadPersistente;

@Entity
public class Regla extends EntidadPersistente {

	@OneToOne(cascade = CascadeType.ALL)
	private Condicion condicion;
	@ManyToOne
	private Actuador actuador;
	@ManyToMany
	private List<DispositivoInteligente> dispositivos;
	
	public Regla(Condicion condicion, Actuador actuador, List<DispositivoInteligente> dispositivos) {
		
		this.condicion = condicion;
		this.actuador = actuador;
		this.dispositivos = dispositivos;
	}
	
	@SuppressWarnings("unused")
	private Regla() { }

	public void ejecutarSiCorresponde() {
		
		if(condicion.seCumple())
			dispositivos.forEach(dispositivo -> actuador.ejecutar(dispositivo));
	}

	public Condicion getCondicion() {
		
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		
		this.condicion = condicion;
	}
}