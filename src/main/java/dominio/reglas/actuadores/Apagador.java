package dominio.reglas.actuadores;

import dominio.dispositivos.DispositivoInteligente;

public class Apagador extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.apagar();
	}
}