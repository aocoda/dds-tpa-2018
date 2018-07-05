package dominio.entrega2.iteracion2;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.DispositivoInteligente;

public class ConsumoTotal implements TipoRestriccion {

	@Override
	public ToDoubleFunction<? super DispositivoInteligente> generadorCoeficientes() {
		
		return dispositivo -> dispositivo.getConsumoPorHora();
	}
}