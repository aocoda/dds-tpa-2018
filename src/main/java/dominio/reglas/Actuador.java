package dominio.reglas;

import dominio.dispositivos.DispositivoInteligente;

public interface Actuador {

	public void ejecutar(DispositivoInteligente dispositivo);
}