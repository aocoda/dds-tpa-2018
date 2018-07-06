package dominio.entrega2.iteracion3;

import java.util.List;

import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.entrega2.iteracion2.AsesorDeUso;
import dominio.reglas.condiciones.Condicion;

public class CondicionSimplex implements Condicion {

	private AsesorDeUso asesor;
	private DispositivoInteligente dispositivo;
	private List<DispositivoInteligente> dispositivos;
	private Periodo periodo;

	public CondicionSimplex(AsesorDeUso asesor, DispositivoInteligente dispositivo, List<DispositivoInteligente> dispositivos, Periodo periodo) {
		
		this.asesor = asesor;
		this.dispositivo = dispositivo;
		this.dispositivos = dispositivos;
		this.periodo = periodo;
	}

	@Override
	public boolean seCumple() {
		
		/*
		
		return new MenorA(0)
				.aplicarCon(asesor.recomendacionesPara(dispositivos, periodo).get(dispositivo));
		
		
		OTRA FORMA DE HACER LO MISMO, MAS COMPLEJA PERO MAS FLEXIBLE:
			Ya no estoy atado a que la diferencia sea menor a cero
			si lo subo al cosntructor puedo poner cualquier relacion: menor, mayor, igual, entre, etc
		*/
		
		return asesor.superaHorasOptimas(dispositivo, dispositivos, periodo);
	}
}