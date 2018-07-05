package dominio.entrega2.iteracion3;

import dominio.entrega2.iteracion2.AsesorDeUso;
import dominio.reglas.condiciones.Condicion;

public class CondicionSimplex implements Condicion {

	@Override
	public boolean seCumple() {
		
		return new AsesorDeUso(restricciones).superaHorasOptimas(dispositivo, dispositivos, unPeriodo);
	}
}