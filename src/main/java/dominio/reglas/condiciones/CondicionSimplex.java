package dominio.reglas.condiciones;

import java.util.List;

import dominio.asesorDeUso.AsesorDeUso;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import dominio.reglas.condiciones.relaciones.MenorA;

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
		
		return new MenorA(0).aplicarCon(asesor.recomendacionesPara(dispositivos, periodo).get(dispositivo));
	}
}