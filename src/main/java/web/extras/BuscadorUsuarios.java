package web.extras;

import java.util.Optional;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;

public class BuscadorUsuarios {

	private RepositorioUsuarios repositorioUsuarios;

	public BuscadorUsuarios(RepositorioUsuarios repositorioUsuarios) {
		
		this.repositorioUsuarios = repositorioUsuarios;
	}

	public Optional<Usuario> getUsuarioLogueado(Request request, Response response) {
		
		String nombreUsuario = request.session().attribute("usuarioActual");
		
		return repositorioUsuarios.getPorNombreDeUsuario(nombreUsuario);
	}
}