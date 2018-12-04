package web;

import java.time.LocalDate;
import java.util.ArrayList;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.Administrador;
import dominio.Categoria;
import dominio.Cliente;
import dominio.TipoCategoria;
import dominio.TipoDocumento;
import dominio.Transformador;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.PeriodoUtils;
import dominio.geoposicionamiento.Coordenada;

public class Bootstrap implements WithGlobalEntityManager, TransactionalOps {

	private static Bootstrap bootstrap = new Bootstrap();
	
	public static void main(String[] args) {
		
		Categoria categoria = new Categoria(TipoCategoria.R1, 0, 1, 500, 1000);
		
		//Sin categoria, Sin dispositivos, Asociado a "Transformador N# 1"
		Cliente german = new Cliente("German Gallici", TipoDocumento.DNI, 38444069, "011-15-5638-5838", "Av. Siempreviva 742",
				LocalDate.of(2018, 1, 1), null, new ArrayList<>(), new ArrayList<>(), new Coordenada(11, 11), "ggallici", "abc123");
		
		//Con categoria, Con dispositivos, Asociado a "Transformador N# 2"
		Cliente agustin = new Cliente("Agustin Coda", TipoDocumento.LE, 38768543, "011-4943-4769", "P. Sherman, Calle Wallaby 42, Sydney", 
				LocalDate.now(), categoria, new ArrayList<>(), new ArrayList<>(), new Coordenada(1, 1), "acoda", "123abc");
		
		DispositivoInteligente aire = new DispositivoInteligente("Aire Acondicionado Whirpool", 1.613, 90, 360);
		aire.encender();
		aire.agregarUso(PeriodoUtils.deLasUltimasNHoras(100));
		
		DispositivoInteligente lavarropas = new DispositivoInteligente("Lavarropas Gama", 0.875, 6, 30);
		lavarropas.encender();
		lavarropas.agregarUso(PeriodoUtils.deLasUltimasNHoras(6));
		
		agustin.registrarDispositivo(aire);
		agustin.registrarDispositivo(lavarropas);
		
		Administrador administrador = new Administrador("German Coda", "Calle Falsa 123", LocalDate.of(2015, 8, 23), "Administrador", "12345678");
		
		Transformador transformador1 = new Transformador("Transformador N# 1", new Coordenada(10, 10));
		Transformador transformador2 = new Transformador("Transformador N# 2", new Coordenada(0, 0));
		
		bootstrap.withTransaction(() -> {
			
			bootstrap.entityManager().persist(categoria);
			bootstrap.entityManager().persist(german);
			bootstrap.entityManager().persist(agustin);
			bootstrap.entityManager().persist(administrador);
			bootstrap.entityManager().persist(transformador1);
			bootstrap.entityManager().persist(transformador2);			
		});
	}	
}