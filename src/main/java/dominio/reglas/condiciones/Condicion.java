package dominio.reglas.condiciones;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import repositorios.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_condicion")
public abstract class Condicion extends EntidadPersistente {

	public abstract boolean seCumple();
}