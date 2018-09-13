package dominio.geoposicionamiento;

import static java.lang.Math.*;

import javax.persistence.Embeddable;

@Embeddable
public class Coordenada {

	public static final double RADIO_DE_LA_TIERRA = 6371;
	
	private double latitud;
	private double longitud;
	
	public Coordenada(double latitud, double longitud) {
		
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	@SuppressWarnings("unused")
	private Coordenada() { }
	
	public double distanciaA(Coordenada otraCoordenada) {
		
		double diferenciaLatitud = toRadians(otraCoordenada.getLatitud() - latitud);
		
		double diferenciaLongitud = toRadians(otraCoordenada.getLongitud() - longitud);
		
		double a = pow(sin(diferenciaLatitud / 2), 2) + 
				
				   cos(toRadians(latitud)) * cos(toRadians(otraCoordenada.getLatitud())) * 
				
				   pow(sin(diferenciaLongitud / 2), 2);
		
		double distanciaAngular = 2 * atan2(sqrt(a), sqrt(1 - a));
		
		return RADIO_DE_LA_TIERRA * distanciaAngular;
	}

	public double getLatitud() {
		
		return latitud;
	}
	
	public double getLongitud() {
		
		return longitud;
	}
}