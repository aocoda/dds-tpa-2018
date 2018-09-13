package dominio.reglas.actuadores;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import dominio.dispositivos.DispositivoInteligente;

@Entity
@DiscriminatorValue(value = "APAGADOR")
public class Apagador extends Actuador {

	@Override
	public void ejecutar(DispositivoInteligente dispositivo) {
		
		dispositivo.apagar();
	}
}