package web.controllers.privados.administrador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.dispositivos.Periodo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.extras.ParserPeriodos;
import web.viewModels.TransformadorVM;

public class TransformadoresController extends VistaAdministradorController {

	private RepositorioClientes repositorioClientes;
	private RepositorioTransformadores repositorioTransformadores;

	public TransformadoresController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes,
			RepositorioTransformadores repositorioTransformadores) {

		super(repositorioUsuarios);

		this.repositorioClientes = repositorioClientes;
		this.repositorioTransformadores = repositorioTransformadores;
	}

	@Override
	protected void agregarDatosDelAdministrador(Map<String, Object> viewModel, Request request, Response response) {
		
		String periodo = request.queryParams("periodo");
		
		Periodo mes = new ParserPeriodos().parsear(periodo);
		
		List<TransformadorVM> transformadores = repositorioTransformadores
				.getAllInstances()
				.stream()
				.map(transformador -> new TransformadorVM(transformador, mes, repositorioClientes.getAllInstances(), repositorioTransformadores.getAllInstances()))
				.collect(Collectors.toList());
		
		viewModel.put("transformadores", transformadores);
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/administrador/transformadores.hbs";
	}
}