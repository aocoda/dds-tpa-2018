package web.controllers.privados.cliente;

import java.util.Map;

import dominio.Cliente;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;


public class DispositivoNuevoController extends VistaClienteController {

	public DispositivoNuevoController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}
	
	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request, Response response) {
	
		viewModel.put("url_agregar_dispositivo", request.uri().replace("/nuevo", ""));
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/cliente/dispositivoNuevo.hbs";
	}
}