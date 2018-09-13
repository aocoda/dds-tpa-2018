package dominio.reglas.actuadores;

import dominio.dispositivos.DispositivoInteligente;

public class Encendedor extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.encender();
	}
}