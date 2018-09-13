package dominio.reglas.actuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "AHORRADOR")
public class Ahorrador extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.modoAhorro();
	}
}