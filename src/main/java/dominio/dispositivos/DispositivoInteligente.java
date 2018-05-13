package dominio.dispositivos;

public class DispositivoInteligente {

	private EstadoDispositivo modoDeOperacion = new Apagado();

	public void cambiarEstado(EstadoDispositivo nuevoModo) {
		this.modoDeOperacion = nuevoModo;
	}

	public void encender() {
		modoDeOperacion.encender(this);
	}

	public void apagar() {
		modoDeOperacion.apagar(this);
	}

	public void modoAhorro() {
		modoDeOperacion.modoAhorro(this);
	}

	public boolean estaEncendido() {
		return modoDeOperacion.estaEncendido();
	}

}