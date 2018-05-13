package dominio.dispositivos;
import DispositivoInteligente;
import Encendido;
import EstadoDispositivo;

public class Apagado implements EstadoDispositivo {

	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Encendido());
	}
	
	public void apagar(DispositivoInteligente dispositivo) {
	}
	
	public void modoAhorro(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Ahorro());
	}
}