package dominio.entrega2.iteracion2;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.DispositivoInteligente;

public interface TipoRestriccion {

	public ToDoubleFunction<? super DispositivoInteligente> generadorCoeficientes();
}