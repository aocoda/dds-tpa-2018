package web.controllers.privados;

import java.util.Map;

import dominio.autenticacion.Usuario;
import repositorios.RepositorioUsuarios;
import spark.Request;

public class PerfilController extends VistaUsuariosController {

	private boolean esAdministrador;
	private String recursoActual;

	public PerfilController(RepositorioUsuarios repositorioUsuarios) {
		
		super(repositorioUsuarios);
	}

	@Override
	protected void agregarDatos(Map<String, Object> viewModel, Request request) {
		
		Usuario usuarioActual = getUsuarioLogueado(request).get();
		
		esAdministrador = usuarioActual.esAdministrador();
		recursoActual = request.uri();
		
		viewModel.put("perfil", usuarioActual);
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return esAdministrador && estaRevisandoSuPerfil() ? "/vistas/privadas/administrador/perfil.hbs" : "/vistas/privadas/cliente/perfil.hbs";
	}
	
	private boolean estaRevisandoSuPerfil() {
		
		return recursoActual.equals("/perfil");
	}
}