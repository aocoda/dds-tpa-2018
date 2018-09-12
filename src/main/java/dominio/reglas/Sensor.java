package dominio.reglas;

import javax.persistence.Entity;

import repositorios.EntidadPersistente;

@Entity
public abstract class Sensor extends EntidadPersistente {

	public abstract double medir();
}