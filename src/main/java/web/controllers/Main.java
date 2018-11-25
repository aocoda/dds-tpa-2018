package web.controllers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.dispositivos.Dispositivo;
import dominio.dispositivos.DispositivoInteligente;

public class Main implements WithGlobalEntityManager, TransactionalOps{

	public static void main(String[] args) {
		
		DispositivoInteligente sa = new Main().entityManager().find(DispositivoInteligente.class, 21l);
		
		new Main().withTransaction(() -> new Main().entityManager().remove(sa));
		
	}

}
