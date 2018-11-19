package repositorios;

import java.util.Optional;

import dominio.autenticacion.Usuario;

public class RepositorioUsuarios extends RepositorioGenerico<Usuario> {

	public Optional<Usuario> getPorNombreDeUsuario(String nombreUsuario) {
		
		return getAllInstances()
				.stream()
				.filter(usuario -> usuario.getNombreUsuario().equals(nombreUsuario))
				.findFirst();
	}

	@Override
	protected Class<Usuario> getClase() {
		
		return Usuario.class;
	}
}