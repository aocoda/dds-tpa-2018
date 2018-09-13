package dominio.reglas.actuadores;

import dominio.dispositivos.DispositivoInteligente;

public class Ahorrador extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.modoAhorro();
	}
}