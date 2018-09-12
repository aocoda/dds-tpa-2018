package repositorios;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {

	@Id
	@GeneratedValue
	private long id;
}