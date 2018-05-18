package dominio.mocks;

import dominio.dispositivos.DispositivoInteligente;

public class DispositivoMock2 extends DispositivoInteligente {

	private String otroAtributo;

	public DispositivoMock2(String nombreGenerico, double consumoPorHora, String otroAtributo) {
		
		super(nombreGenerico, consumoPorHora);
		
		this.otroAtributo = otroAtributo;
	}

	public String getOtroAtributo() {
	
		return otroAtributo;
	}
}