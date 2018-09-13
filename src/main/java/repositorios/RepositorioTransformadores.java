package repositorios;

import dominio.Transformador;

public class RepositorioTransformadores extends RepositorioGenerico<Transformador> {

	@Override
	protected Class<Transformador> getClase() {
		
		return Transformador.class;
	} 
}