package dominio.entrega2.iteracion3;

import dominio.entrega2.iteracion2.AsesorDeUso;

public class CondicionSimplex {

	
	public boolean seCumple() {
		
		return new AsesorDeUso(restricciones).superaHorasOptimas(dispositivo, dispositivos, unPeriodo);
	}
}