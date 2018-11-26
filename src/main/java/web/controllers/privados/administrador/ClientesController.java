package web.controllers.privados.administrador;

import java.util.Map;

import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;

public class ClientesController extends VistaAdministradorController {

	private RepositorioClientes repositorioClientes;

	public ClientesController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios);
		
		this.repositorioClientes = repositorioClientes;
	}

	@Override
	protected void agregarDatosDelAdministrador(Map<String, Object> viewModel, Request request, Response response) {
		
		viewModel.put("clientes", repositorioClientes.getAllInstances());
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/administrador/clientes.hbs";
	}
}