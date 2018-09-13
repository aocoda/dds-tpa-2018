package repositorios;

import dominio.reglas.actuadores.Actuador;

public class RepositorioActuadores extends RepositorioGenerico<Actuador>{

	@Override
	protected Class<Actuador> getClase() {
		
		return Actuador.class;
	} 
}