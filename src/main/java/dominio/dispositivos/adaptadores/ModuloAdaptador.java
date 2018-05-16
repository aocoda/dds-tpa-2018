package dominio.dispositivos.adaptadores;

import dominio.dispositivos.*;

public interface ModuloAdaptador {
	
	public DispositivoInteligente adaptar(DispositivoEstandar dispositivo);
}