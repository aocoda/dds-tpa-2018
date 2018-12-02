package web.controllers.privados.administrador;

import java.util.HashMap;
import java.util.Map;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.extras.BuscadorUsuarios;

public abstract class VistaAdministradorController extends BuscadorUsuarios {

	public VistaAdministradorController(RepositorioUsuarios repositorioUsuarios) {
		
		super(repositorioUsuarios);
	}
	
	public ModelAndView renderizarVista(Request request, Response response) {
		
		Usuario usuarioActual = getUsuarioLogueado(request).get();
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("esAdministrador", usuarioActual.esAdministrador());
		viewModel.put("nombreUsuarioActual", usuarioActual.getNombreUsuario());

		agregarDatosDelAdministrador(viewModel, request);
		
		return new ModelAndView(viewModel, getUbicacionDelTemplate());
	}
	
	protected abstract void agregarDatosDelAdministrador(Map<String, Object> viewModel, Request request);
	
	protected abstract String getUbicacionDelTemplate();
}
