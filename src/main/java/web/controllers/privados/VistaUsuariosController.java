package web.controllers.privados;

import java.util.HashMap;
import java.util.Map;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.extras.BuscadorUsuarios;

public abstract class VistaUsuariosController extends BuscadorUsuarios {

	public VistaUsuariosController(RepositorioUsuarios repositorioUsuarios) {
		
		super(repositorioUsuarios);
	}

	
	public ModelAndView renderizarVista(Request request, Response response) {
		
		Usuario usuarioActual = getUsuarioLogueado(request, response).get();
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("esAdministrador", usuarioActual.esAdministrador());
		viewModel.put("nombreUsuarioActual", usuarioActual.getNombreUsuario());

		agregarDatos(viewModel, request, response);
		
		return new ModelAndView(viewModel, getUbicacionDelTemplate());
	}
	
	protected abstract void agregarDatos(Map<String, Object> viewModel, Request request, Response response);
	
	protected abstract String getUbicacionDelTemplate();
}