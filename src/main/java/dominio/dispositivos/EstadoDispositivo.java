package dominio.dispositivos;

abstract class EstadoDispositivo {
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.cambiarEstado(new Encendido());
	};

	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.cambiarEstado(new Apagado());
	}
	
	public void modoAhorro(DispositivoInteligente dispositivo) {
		dispositivo.cambiarEstado(new Ahorro());
	}

	public boolean estaEncendido() {
		return false;
	}
}