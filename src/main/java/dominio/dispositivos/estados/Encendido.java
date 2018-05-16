package dominio.dispositivos.estados;

import dominio.dispositivos.DispositivoInteligente;

public class Encendido extends EstadoDispositivo {

	public void encender(DispositivoInteligente dispositivo) { }

	public boolean estaEncendido() {
		
		return true;
	}
}