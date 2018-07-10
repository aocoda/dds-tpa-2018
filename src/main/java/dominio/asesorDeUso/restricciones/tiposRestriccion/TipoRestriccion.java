package dominio.asesorDeUso.restricciones.tiposRestriccion;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.DispositivoInteligente;

public interface TipoRestriccion {

	public ToDoubleFunction<? super DispositivoInteligente> generadorCoeficientes();
}