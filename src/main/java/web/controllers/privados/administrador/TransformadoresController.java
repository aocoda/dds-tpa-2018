package web.controllers.privados.administrador;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.dispositivos.Periodo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.Request;
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
	protected void agregarDatosDelAdministrador(Map<String, Object> viewModel, Request request) {
		
		String periodoString = request.queryParams("periodo");
		
		ParserPeriodos parserPeriodos = new ParserPeriodos();
		
		Periodo periodo = parserPeriodos.parsear(periodoString);
		
		List<TransformadorVM> transformadores = repositorioTransformadores
				.getAllInstances()
				.stream()
				.map(transformador -> new TransformadorVM(transformador, periodo, repositorioClientes.getAllInstances(), repositorioTransformadores.getAllInstances()))
				.collect(Collectors.toList());
		
		viewModel.put("transformadores", transformadores);
		viewModel.put("periodo", parserPeriodos.parsear(periodo));
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/administrador/transformadores.hbs";
	}
}