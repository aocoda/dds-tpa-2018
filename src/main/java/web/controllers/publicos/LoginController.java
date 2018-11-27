package web.controllers.publicos;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {
	
	private RepositorioUsuarios repositorioUsuarios;

	public LoginController(RepositorioUsuarios repositorio) {
		
		this.repositorioUsuarios = repositorio;
	}

	public ModelAndView renderizarVista(Request request, Response response) {
		
		String usuarioActual = request.session().attribute("usuarioActual");
		
		if(usuarioActual != null) {
			
			response.redirect("/home");
		}
		
		return new ModelAndView(null, "/vistas/publicas/login.hbs");
	}
	
	public Void login(Request request, Response response) {
		
		String nombreUsuario = request.queryParams("nombreUsuario");
		String contrasenia = request.queryParams("contrasenia");
		
		repositorioUsuarios.getPorNombreDeUsuario(nombreUsuario).ifPresent(usuario -> {

			if (usuario.esContraseniaValida(contrasenia)) {

				request.session().attribute("usuarioActual", nombreUsuario);

				response.redirect("/home");
			}
			else {
				
				//Si el la contrasenia no es correcta mostrar error
				response.redirect("/login");
			}
		});
		
		//Si el usuario no existe mostrar error
		response.redirect("/login");
		
		return null;
	}
	
	public Void logout(Request request, Response response) {
		
		request.session().removeAttribute("usuarioActual");
		
		response.redirect("/home");
		
		return null;
	}
}