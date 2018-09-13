package dominio.geoposicionamiento;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Area {

	@Embedded
	private Coordenada coordenada;
	private double radio;
	
	public Area(Coordenada coordenada, double radio) {
		
		this.coordenada = coordenada;
		this.radio = radio;
	}
	
	@SuppressWarnings("unused")
	private Area() { }
	
	public boolean contieneA(Coordenada unaCoordenada) {
		
		return coordenada.distanciaA(unaCoordenada) < radio;
	}
}