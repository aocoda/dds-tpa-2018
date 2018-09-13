package dominio.reglas.actuadores;

import dominio.dispositivos.DispositivoInteligente;
import repositorios.EntidadPersistente;

public abstract class Actuador extends EntidadPersistente {

	public abstract void ejecutar(DispositivoInteligente dispositivo);
}