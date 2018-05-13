package dominio.dispositivos;
import DispositivoInteligente;
import EstadoDispositivo;

public class Ahorro implements EstadoDispositivo {

	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Encendido());
	}

	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Apagado());
	}

	public void modoAhorro(DispositivoInteligente dispositivo) {
	}
}
