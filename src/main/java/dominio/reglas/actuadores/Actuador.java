package dominio.reglas.actuadores;

import dominio.dispositivos.DispositivoInteligente;

public interface Actuador<T extends DispositivoInteligente> {

	public void ejecutar(T dispositivoInteligente);
}