package dominio.asesorDeUso.restricciones.tiposRestriccion;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.Dispositivo;

public class ConsumoTotal implements TipoRestriccion {

	@Override
	public ToDoubleFunction<? super Dispositivo> generadorCoeficientes() {
		
		return dispositivo -> dispositivo.getConsumoPorHora();
	}
}