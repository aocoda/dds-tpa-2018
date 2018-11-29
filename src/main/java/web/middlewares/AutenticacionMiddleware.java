package web.middlewares;

import java.util.Optional;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.extras.BuscadorUsuarios;
import web.extras.NotFoundException;

public class AutenticacionMiddleware extends BuscadorUsuarios {

	public AutenticacionMiddleware(RepositorioUsuarios repositorioUsuarios) {

		super(repositorioUsuarios);
	}
	
	public void asegurarPermisosDeAdministrador(Request request, Response response) {

		Optional<Usuario> optUsuario = getUsuarioLogueado(request);

		if (optUsuario.isPresent()) {
			
			if (!optUsuario.get().esAdministrador())
				
				throw new NotFoundException("El usuario no tiene permisos de administrador");
		}	
		else
			throw new NotFoundException("No existe usuario logueado");
	}

	public void asegurarPermisosDeCliente(Request request, Response response) {

		Optional<Usuario> optUsuario = getUsuarioLogueado(request);

		if (optUsuario.isPresent()) {
			
			if (optUsuario.get().esAdministrador())
				
				throw new NotFoundException("El usuario no tiene permisos de cliente");
		}	
		else
			throw new NotFoundException("No existe usuario logueado");
	}
	
	public void asegurarUsuarioLogueado(Request request, Response response) {

		Optional<Usuario> optUsuario = getUsuarioLogueado(request);

		if (!optUsuario.isPresent())
			throw new NotFoundException("No existe usuario logueado");
	}
}