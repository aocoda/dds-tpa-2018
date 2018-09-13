package dominio.reglas.actuadores;

import javax.persistence.Entity;

import dominio.dispositivos.DispositivoInteligente;
import repositorios.EntidadPersistente;

@Entity
public abstract class Actuador extends EntidadPersistente {

	public abstract void ejecutar(DispositivoInteligente dispositivo);
}