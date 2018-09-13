package dominio.reglas.actuadores;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import dominio.dispositivos.DispositivoInteligente;
import repositorios.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_actuador")
public abstract class Actuador extends EntidadPersistente {

	public abstract void ejecutar(DispositivoInteligente dispositivo);
}