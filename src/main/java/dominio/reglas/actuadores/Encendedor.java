package dominio.reglas.actuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "ENCENDEDOR")
public class Encendedor extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.encender();
	}
}