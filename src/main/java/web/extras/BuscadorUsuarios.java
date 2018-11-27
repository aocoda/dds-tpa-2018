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
		
		String posibleNombreUsuario = request.session().attribute("usuarioActual");
		
		return Optional
				.ofNullable(posibleNombreUsuario)
				.map(nombreUsuario -> repositorioUsuarios.getPorNombreDeUsuario(nombreUsuario))
				.orElse(Optional.empty());
	}
}