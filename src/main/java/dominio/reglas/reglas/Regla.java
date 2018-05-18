package dominio.reglas.reglas;

import java.util.Collection;

import dominio.dispositivos.DispositivoInteligente;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.sensores.condiciones.Condicion;

public class Regla<T extends DispositivoInteligente> {

	private Collection<Condicion> condiciones;
	private Collection<Actuador<T>> actuadores;
	private T dispositivoInteligente;
	
	public void ejecutarActuadoresSiCorresponde() {
		
		if(seCumplenTodas(condiciones))
			ejecutarTodos(actuadores, dispositivoInteligente);
	}

	private boolean seCumplenTodas(Collection<Condicion> condiciones) {
		
		return condiciones.stream().allMatch(condicion -> condicion.seCumple());
	}
	
	private void ejecutarTodos(Collection<Actuador<T>> actuadores, T dispositivoInteligente) {
		
		actuadores.forEach(actuador -> actuador.ejecutar(dispositivoInteligente));
	}
}