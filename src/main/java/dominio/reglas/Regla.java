package dominio.reglas;

import java.util.Collection;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.condiciones.Condicion;

public class Regla<T extends DispositivoInteligente> {

	private Condicion condicion;
	private Collection<Actuador<T>> actuadores;
	private T dispositivoInteligente;
	
	public Regla(Condicion condicion, Collection<Actuador<T>> actuadores, T dispositivoInteligente) {
		
		this.condicion = condicion;
		this.actuadores = actuadores;
		this.dispositivoInteligente = dispositivoInteligente;
	}

	public void ejecutarActuadoresSiCorresponde() {
		
		if(condicion.seCumple())
			ejecutarTodos(actuadores, dispositivoInteligente);
	}
	
	private void ejecutarTodos(Collection<Actuador<T>> actuadores, T dispositivoInteligente) {
		
		actuadores.forEach(actuador -> actuador.ejecutar(dispositivoInteligente));
	}
}