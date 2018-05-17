package dominio.dispositivos.inteligentes;

import java.util.Collection;
import java.util.Collections;

public interface StreamUtils<T extends Comparable<T>> {

	default public boolean esElPrimeroDeLaLista(T elemento, Collection<T> lista) {
		
		return lista.stream().sorted().findFirst().get().equals(elemento);
	}
	
	default public boolean esElUltimoDeLaLista(T elemento, Collection<T> lista) {
		
		return lista.stream().sorted(Collections.reverseOrder()).findFirst().get().equals(elemento);
	}
}