package web.controllers.privados.cliente;

import java.util.Map;

import dominio.Cliente;
import dominio.autenticacion.Usuario;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import web.controllers.privados.VistaUsuariosController;
import web.extras.NotFoundException;

public abstract class VistaClienteController extends VistaUsuariosController {
	
	private RepositorioClientes repositorioClientes;

	public VistaClienteController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios);
		
		this.repositorioClientes = repositorioClientes;
	}
	
	protected void agregarDatos(Map<String, Object> viewModel, Request request) {
		
		Cliente cliente = getCliente(request);
		
		viewModel.put("idCliente", cliente.getId());
		viewModel.put("nombreCliente", cliente.getNombreCompleto());
		
		agregarDatosDelCliente(viewModel, cliente, request);
	}

	protected Cliente getCliente(Request request) {
		
		Usuario usuarioActual = getUsuarioLogueado(request).get();
		
		if(usuarioActual.esAdministrador()) {
			
			long idCliente = Long.valueOf(request.params("id"));
			
			return repositorioClientes
					.getPorId(idCliente)
					.orElseThrow(() -> new NotFoundException("No existe cliente con id: " + idCliente));
		}
		
		return (Cliente) usuarioActual;
	}
	
	protected abstract void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request);
}