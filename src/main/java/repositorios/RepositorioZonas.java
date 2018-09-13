package repositorios;

import dominio.Zona;

public class RepositorioZonas extends RepositorioGenerico<Zona> {

	@Override
	protected Class<Zona> getClase() {
		
		return Zona.class;
	} 
}