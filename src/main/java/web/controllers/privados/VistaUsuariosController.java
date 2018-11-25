package web.controllers.privados;

import java.util.HashMap;
import java.util.Map;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class VistaUsuariosController {

	private RepositorioUsuarios repositorioUsuarios;

	public VistaUsuariosController(RepositorioUsuarios repositorioUsuarios) {
		
		this.repositorioUsuarios = repositorioUsuarios;
	}

	
	public ModelAndView renderizarVista(Request request, Response response) {
		
		Usuario usuarioActual = getUsuarioLogueado(request, response);
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("esAdministrador", usuarioActual.esAdministrador());
		viewModel.put("nombreUsuarioActual", usuarioActual.getNombreUsuario());

		agregarDatos(viewModel, request, response);
		
		return new ModelAndView(viewModel, getUbicacionDelTemplate());
	}

	protected Usuario getUsuarioLogueado(Request request, Response response) {
		
		String nombreUsuario = request.session().attribute("usuarioActual");
		
		if(nombreUsuario == null) {
			
			response.redirect("/login");
		}
		
		return repositorioUsuarios.getPorNombreDeUsuario(nombreUsuario).get();
	}
	
	protected abstract void agregarDatos(Map<String, Object> viewModel, Request request, Response response);
	
	protected abstract String getUbicacionDelTemplate();
}