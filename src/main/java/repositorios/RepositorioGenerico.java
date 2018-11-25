package repositorios;

import java.util.List;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public abstract class RepositorioGenerico<E extends EntidadPersistente> implements WithGlobalEntityManager {
	
	public List<E> getAllInstances() {
		
		return entityManager()
				.createQuery("FROM " + getClase().getSimpleName(), getClase())
				.getResultList();
	}
	
	public Optional<E> getPorId(long id) {
		
		return Optional.ofNullable(entityManager().find(getClase(), id));
	}	
	
	public void agregar(E unElemento) {
		
		entityManager().persist(unElemento);
	}
		
	public void borrar(E unElemento) {
		
		entityManager().remove(unElemento);
	}
	
	protected abstract Class<E> getClase();
}