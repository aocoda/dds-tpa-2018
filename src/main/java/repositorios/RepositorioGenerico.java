package repositorios;

import java.util.Collection;
import java.util.HashSet;

abstract class RepositorioGenerico<E> {

	protected Collection<E> elementos = new HashSet<>();
	
	public Collection<E> getAllInstances() {
		
		return elementos;
	}	
	
	public void agregar(E unElemento) {
		
		elementos.add(unElemento);
	}
		
	public void borrar(E unElemento) {
		
		elementos.remove(unElemento);
	}
}