package dominio.dispositivos.inteligentes;

import dominio.dispositivos.DispositivoInteligente;

public enum EstadoDispositivo {
	
	AHORRO {
		
		@Override
		public void modoAhorro(DispositivoInteligente dispositivo) { }
	}
	, 
	APAGADO {
		
		@Override
		public void apagar(DispositivoInteligente dispositivo) { }
		
		@Override
		public boolean estaApagado() {
			
			return true;
		}
	}
	,
	ENCENDIDO {
		
		@Override
		public void encender(DispositivoInteligente dispositivo) { }

		@Override
		public boolean estaEncendido() {
			
			return true;
		}
	}
	
	;
	
	public void encender(DispositivoInteligente dispositivo) {
		
		dispositivo.cambiarEstado(ENCENDIDO);
	}

	public void apagar(DispositivoInteligente dispositivo) {
		
		dispositivo.cambiarEstado(APAGADO);
	}
	
	public void modoAhorro(DispositivoInteligente dispositivo) {
		
		dispositivo.cambiarEstado(AHORRO);
	}

	public boolean estaEncendido() {
		
		return false;
	}
	
	public boolean estaApagado() {
		
		return false;
	}
}