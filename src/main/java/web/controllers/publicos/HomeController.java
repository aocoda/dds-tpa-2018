package web.controllers.publicos;

import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import web.extras.BuscadorUsuarios;

public class HomeController extends BuscadorUsuarios {

	public HomeController(RepositorioUsuarios repositorioUsuarios) {
		
		super(repositorioUsuarios);
	}

	public ModelAndView renderizarVista(Request request, Response response) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		getUsuarioLogueado(request, response).ifPresent(usuarioActual -> {
			
			viewModel.put("nombreUsuarioActual", usuarioActual.getNombreUsuario());
			viewModel.put("esAdministrador", usuarioActual.esAdministrador());	
		});
		
		return new ModelAndView(viewModel, "/vistas/publicas/home.hbs");
	}
}