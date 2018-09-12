package dominio.reglas.condiciones;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import repositorios.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Condicion extends EntidadPersistente {

	public abstract boolean seCumple();
}