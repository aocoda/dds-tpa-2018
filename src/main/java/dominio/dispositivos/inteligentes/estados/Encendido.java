package dominio.dispositivos.inteligentes.estados;

import dominio.dispositivos.DispositivoInteligente;

public class Encendido extends EstadoDispositivo {

	public void encender(DispositivoInteligente dispositivo) { }

	public boolean estaEncendido() {
		
		return true;
	}
}