package web.viewModels;

import java.util.List;

import dominio.Cliente;
import dominio.Transformador;
import dominio.dispositivos.Periodo;

public class TransformadorVM {

	private Transformador transformador;
	private Periodo periodo;
	private List<Cliente> clientes;
	private List<Transformador> transformadores;

	public TransformadorVM(Transformador transformador, Periodo periodo, List<Cliente> clientes, List<Transformador> transformadores) {

		this.transformador = transformador;
		this.periodo = periodo;
		this.clientes = clientes;
		this.transformadores = transformadores;
	}
	
	public long getCantidadClientesConectados() {
		
		return transformador.clientesAsociados(clientes, transformadores).size();
	}
	
	public double getConsumo() {
		
		return transformador.consumoDe(periodo, clientes, transformadores);
	}

	public Transformador getTransformador() {
		
		return transformador;
	}
}