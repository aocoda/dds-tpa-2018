package dominio.asesorDeUso.restricciones;

import java.util.Collection;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;

import dominio.asesorDeUso.restricciones.tiposRestriccion.TipoRestriccion;
import dominio.dispositivos.DispositivoInteligente;

public class Restriccion {
	
	private TipoRestriccion tipoRestriccion;
	private Relationship relacion;
	private Double valor;
	
	public Restriccion(TipoRestriccion tipoRestriccion, Relationship relacion, double valor) {

		this.tipoRestriccion = tipoRestriccion;
		this.relacion = relacion;
		this.valor = valor;
	}
	
	public LinearConstraint toLinearConstraint(Collection<DispositivoInteligente> dispositivos) {
		
		double [] coeficientes =  dispositivos.stream().mapToDouble(tipoRestriccion.generadorCoeficientes()).toArray();
		
		return new LinearConstraint(coeficientes, relacion, valor);
	}
}