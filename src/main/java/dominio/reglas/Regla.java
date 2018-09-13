package dominio.reglas;

import java.util.Collection;

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

	@OneToOne
	private Condicion condicion;
	@ManyToOne
	private Actuador actuador;
	@ManyToMany
	private Collection<DispositivoInteligente> dispositivos;
	
	public Regla(Condicion condicion, Actuador actuador, Collection<DispositivoInteligente> dispositivos) {
		
		this.condicion = condicion;
		this.actuador = actuador;
		this.dispositivos = dispositivos;
	}

	public void ejecutarSiCorresponde() {
		
		if(condicion.seCumple())
			dispositivos.forEach(dispositivo -> actuador.ejecutar(dispositivo));
	}
}