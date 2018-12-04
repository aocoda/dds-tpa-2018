package web.controllers.privados.cliente;

import java.util.Map;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.Cliente;
import dominio.Transformador;
import dominio.excepciones.SinTransformadoresCercanosException;
import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;

public class PerfilController extends VistaClienteController implements WithGlobalEntityManager, TransactionalOps {

	private RepositorioTransformadores repositorioTransformadores;

	public PerfilController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes, RepositorioTransformadores repositorioTransformadores) {
		
		super(repositorioUsuarios, repositorioClientes);
		
		this.repositorioTransformadores = repositorioTransformadores;
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request) {

		viewModel.put("cliente", cliente);
		
		try {
			
			Transformador transformador = cliente.transformadorAsociado(repositorioTransformadores.getAllInstances());
			
			viewModel.put("transformador", transformador.getNombre());				
		}
		catch(SinTransformadoresCercanosException e) {
			
			viewModel.put("transformador", e.getMessage());	
		}
		
		viewModel.put("cantidadDispositivos", cliente.cantidadDispositivos());
		viewModel.put("cantidadEstandar", cliente.getDispositivosEstandar().size());
		viewModel.put("cantidadInteligentes", cliente.getDispositivosInteligentes().size());
		viewModel.put("cantidadApagados", cliente.cantidadDispositivosApagados());
		viewModel.put("cantidadEncendidos", cliente.cantidadDispositivosEncendidos());
		viewModel.put("apagadoAutomaticoActivado", cliente.tieneApagadoAutomaticoActivado());
	}
	
	public Void permutarApagadoAutomatico(Request request, Response response) {

		Cliente cliente = getCliente(request);
		
		withTransaction(() -> cliente.permutarApagadoAutomatico());
		
		PerThreadEntityManagers.closeEntityManager();
		
		response.redirect("/perfil");
		
		return null;
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/cliente/perfil.hbs";
	}
}