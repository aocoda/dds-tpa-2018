package dominio.entrega2.iteracion2;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.DispositivoInteligente;

public class Horas implements TipoRestriccion {

	private DispositivoInteligente dispositivo;
	
	public Horas(DispositivoInteligente dispositivo) {
		
		this.dispositivo = dispositivo;
	}
	
	@Override
	public ToDoubleFunction<? super DispositivoInteligente> generadorCoeficientes() {
		
		return unDispositivo -> unDispositivo.equals(dispositivo) ? 1 : 0;
	}
}