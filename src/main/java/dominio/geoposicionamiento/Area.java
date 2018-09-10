package dominio.geoposicionamiento;

public class Area {

	private Coordenada coordenada;
	private double radio;
	
	public Area(Coordenada coordenada, double radio) {
		
		this.coordenada = coordenada;
		this.radio = radio;
	}
	
	public boolean contieneA(Coordenada unaCoordenada) {
		
		return coordenada.distanciaA(unaCoordenada) < radio;
	}
}