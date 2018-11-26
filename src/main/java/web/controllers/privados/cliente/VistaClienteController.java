package web.controllers.privados.cliente;

import java.util.Map;

import dominio.Cliente;
import dominio.autenticacion.Usuario;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.controllers.privados.VistaUsuariosController;
import web.extras.NotFoundException;

public abstract class VistaClienteController extends VistaUsuariosController {
	
	protected RepositorioClientes repositorioClientes;

	public VistaClienteController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios);
		
		this.repositorioClientes = repositorioClientes;
	}
	
	protected void agregarDatos(Map<String, Object> viewModel, Request request, Response response) {
		
		Cliente cliente = getCliente(request, response);
		
		viewModel.put("idCliente", cliente.getId());
		viewModel.put("nombreCliente", cliente.getNombreCompleto());
		
		agregarDatosDelCliente(viewModel, cliente, request, response);
	}

	protected Cliente getCliente(Request request, Response response) {
		
		Usuario usuarioActual = getUsuarioLogueado(request, response).get();
		
		long idCliente = usuarioActual.esAdministrador() ? Long.valueOf(request.params("id")) : usuarioActual.getId();
		
		return repositorioClientes
				.getPorId(idCliente)
				.orElseThrow(() -> new NotFoundException("No existe cliente con id: " + idCliente));
	}
	
	protected abstract void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request, Response response);
}