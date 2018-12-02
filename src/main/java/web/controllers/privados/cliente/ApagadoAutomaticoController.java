package web.controllers.privados.cliente;

import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.Cliente;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;

public class ApagadoAutomaticoController extends VistaClienteController implements TransactionalOps, WithGlobalEntityManager {

	public ApagadoAutomaticoController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}

	public Void permutar(Request request, Response response) {

		Cliente cliente = getCliente(request);
		
		withTransaction(() -> cliente.permutarApagadoAutomatico());
		
		entityManager().close();
		
		response.redirect("/perfil");
		
		return null;
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request) { }

	@Override
	protected String getUbicacionDelTemplate() {
		
		return null;
	}
}
