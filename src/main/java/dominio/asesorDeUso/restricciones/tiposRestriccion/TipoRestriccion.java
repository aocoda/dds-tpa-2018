package dominio.asesorDeUso.restricciones.tiposRestriccion;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.Dispositivo;

public interface TipoRestriccion {

	public ToDoubleFunction<? super Dispositivo> generadorCoeficientes();
}