package dominio.reglas.condiciones.relaciones;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import repositorios.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_relacion")
public abstract class Relacion extends EntidadPersistente {

	public abstract boolean aplicarCon(double valor);
}
