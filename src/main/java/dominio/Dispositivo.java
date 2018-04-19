package dominio;

public class Dispositivo {
	public String nombreGenerico;
	public double ConsumoPorMes;
	public boolean estaEncendido;
	
	Dispositivo(String nombre,double consumo,boolean encendido){
		this.nombreGenerico = nombre;
		this.ConsumoPorMes = consumo;
		this.estaEncendido = encendido;
		
	}

	public String getNombreGenerico() {
		return nombreGenerico;
	}

	public double getConsumoPorMes() {
		return ConsumoPorMes;
	}

	public boolean isEstaEncendido() {
		return estaEncendido;
	}

	
	
}
