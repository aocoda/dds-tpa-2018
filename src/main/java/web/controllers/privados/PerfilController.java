package web.controllers.privados;

import java.util.Map;

import dominio.Cliente;
import dominio.Transformador;
import dominio.autenticacion.Usuario;
import dominio.excepciones.SinTransformadoresCercanosException;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.Request;

public class PerfilController extends VistaUsuariosController {

	private boolean esAdministrador;
	private String recursoActual;
	private RepositorioTransformadores repositorioTransformadores;

	public PerfilController(RepositorioUsuarios repositorioUsuarios, RepositorioTransformadores repositorioTransformadores) {
		
		super(repositorioUsuarios);
		
		this.repositorioTransformadores = repositorioTransformadores;
	}

	@Override
	protected void agregarDatos(Map<String, Object> viewModel, Request request) {
		
		Usuario usuarioActual = getUsuarioLogueado(request).get();
		
		esAdministrador = usuarioActual.esAdministrador();
		recursoActual = request.uri();
		
		if(esAdministrador && estaRevisandoSuPerfil()) {
			
			
		}
		else {
			
			Cliente cliente = (Cliente) usuarioActual;
			
			viewModel.put("cliente", cliente);
			
			try {
				
				Transformador transformador = cliente.transformadorAsociado(repositorioTransformadores.getAllInstances());
				
				viewModel.put("transformador", transformador.getNombre());				
			}
			catch(SinTransformadoresCercanosException e) {
				
				viewModel.put("transformador", e.getMessage());	
			}
			
			viewModel.put("cantidadDispositivos", cliente.cantidadDispositivos());
			viewModel.put("cantidadEstandar", cliente.getDispositivosEstandar().size());
			viewModel.put("cantidadInteligentes", cliente.getDispositivosInteligentes().size());
			viewModel.put("cantidadApagados", cliente.cantidadDispositivosApagados());
			viewModel.put("cantidadEncendidos", cliente.cantidadDispositivosEncendidos());
			viewModel.put("apagadoAutomaticoActivado", cliente.tieneApagadoAutomaticoActivado());
		}
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return esAdministrador && estaRevisandoSuPerfil() ? "/vistas/privadas/administrador/perfil.hbs" : "/vistas/privadas/cliente/perfil.hbs";
	}
	
	private boolean estaRevisandoSuPerfil() {
		
		return recursoActual.equals("/perfil");
	}
}