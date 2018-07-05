package dominio.reglas;

import dominio.dispositivos.DispositivoInteligente;

public interface Actuador<T extends DispositivoInteligente> {

	public void ejecutar(T dispositivoInteligente);
}