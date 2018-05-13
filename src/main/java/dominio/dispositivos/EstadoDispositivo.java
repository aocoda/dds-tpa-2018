package dominio.dispositivos;
import DispositivoInteligente;

public interface EstadoDispositivo {
	public void encender(DispositivoInteligente dispositivo);
	
	public void apagar(DispositivoInteligente dispositivo);
	
	public void modoAhorro(DispositivoInteligente dispositivo);
}

