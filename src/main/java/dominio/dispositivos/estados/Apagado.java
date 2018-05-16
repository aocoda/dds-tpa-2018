package dominio.dispositivos.estados;

import dominio.dispositivos.DispositivoInteligente;

public class Apagado extends EstadoDispositivo {

	public void apagar(DispositivoInteligente dispositivo) {
	}
	
	public boolean estaApagado() {
		return true;
	}
}