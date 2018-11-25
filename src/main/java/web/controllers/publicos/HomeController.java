package web.controllers.publicos;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

	public ModelAndView renderizarVista(Request request, Response response) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("nombreUsuarioActual", request.session().attribute("usuarioActual"));
		
		return new ModelAndView(viewModel, "/vistas/publicas/home.hbs");
	}
}