package dominio.asesorDeUso.restricciones.tiposRestriccion;

import java.util.function.ToDoubleFunction;

import dominio.dispositivos.Dispositivo;

public class HorasDeUso implements TipoRestriccion {

	private Dispositivo dispositivo;
	
	public HorasDeUso(Dispositivo dispositivo) {
		
		this.dispositivo = dispositivo;
	}
	
	@Override
	public ToDoubleFunction<? super Dispositivo> generadorCoeficientes() {
		
		return unDispositivo -> unDispositivo.equals(dispositivo) ? 1 : 0;
	}
}