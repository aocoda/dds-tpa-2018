package web.controllers.privados.administrador;

import java.util.Map;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.controllers.privados.VistaUsuariosController;
import web.extras.NotFoundException;

public abstract class VistaAdministradorController extends VistaUsuariosController {

	public VistaAdministradorController(RepositorioUsuarios repositorioUsuarios) {
		
		super(repositorioUsuarios);
	}

	@Override
	protected void agregarDatos(Map<String, Object> viewModel, Request request, Response response) {
		
		Usuario usuarioActual = getUsuarioLogueado(request, response);
		
		if(!usuarioActual.esAdministrador())
			throw new NotFoundException("El usuario no tiene permisos de administrador");
		
		agregarDatosDelAdministrador(viewModel, request, response);
	}
	
	protected abstract void agregarDatosDelAdministrador(Map<String, Object> viewModel, Request request, Response response);
}