package dominio.entrega2.iteracion2;

import java.util.Collection;

import org.apache.commons.math3.optim.linear.LinearConstraint;

import dominio.dispositivos.DispositivoInteligente;

public abstract class Restriccion {
	
	public abstract LinearConstraint toLinearConstraint(Collection<DispositivoInteligente> dispositivos);
}