package dominio.dispositivos;
import DispositivoInteligente;
import EstadoDispositivo;

public class Encendido implements EstadoDispositivo {

	public void encender(DispositivoInteligente dispositivo) {
	}
	
	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Apagado());
	}
	
	public void modoAhorro(DispositivoInteligente dispositivo) {
		dispositivo.setModoDeOperacion(new Ahorro());
	}
}
