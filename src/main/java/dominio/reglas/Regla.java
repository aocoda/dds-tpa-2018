package dominio.reglas;

import java.util.Collection;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.condiciones.Condicion;

public class Regla {

	private Condicion condicion;
	private Actuador actuador;
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