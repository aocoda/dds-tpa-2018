package dominio.dispositivos.adaptadores;

import dominio.dispositivos.*;
import dominio.excepciones.DispositivoNoAdaptadoException;

public interface ModuloAdaptador {
	
	public DispositivoInteligente adaptar(DispositivoEstandar dispositivo) throws DispositivoNoAdaptadoException;
}