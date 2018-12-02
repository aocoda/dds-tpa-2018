package web.controllers.privados.cliente;

import java.util.HashMap;
import java.util.Map;

import dominio.Cliente;
import dominio.autenticacion.Usuario;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.extras.BuscadorUsuarios;
import web.extras.NotFoundException;

public abstract class VistaClienteController extends BuscadorUsuarios {
	
	private RepositorioClientes repositorioClientes;
	private Usuario usuarioActual;

	public VistaClienteController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios);
		
		this.repositorioClientes = repositorioClientes;
	}
	
	public ModelAndView renderizarVista(Request request, Response response) {
		
		usuarioActual = getUsuarioLogueado(request).get();
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("esAdministrador", usuarioActual.esAdministrador());
		viewModel.put("nombreUsuarioActual", usuarioActual.getNombreUsuario());

		Cliente cliente = getCliente(request);
		
		viewModel.put("idCliente", cliente.getId());
		viewModel.put("nombreCliente", cliente.getNombreCompleto());
		
		agregarDatosDelCliente(viewModel, cliente, request);
		
		return new ModelAndView(viewModel, getUbicacionDelTemplate());
	}
	
	protected Cliente getCliente(Request request) {
		
		if(usuarioActual.esAdministrador()) {
			
			long idCliente = Long.valueOf(request.params("id"));
			
			return repositorioClientes
					.getPorId(idCliente)
					.orElseThrow(() -> new NotFoundException("No existe cliente con id: " + idCliente));
		}
		
		return (Cliente) usuarioActual;
	}
	
	protected abstract void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request);
	
	protected abstract String getUbicacionDelTemplate();
}