package web.controllers.privados.cliente;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.Cliente;
import dominio.asesorDeUso.AsesorDeUso;
import dominio.dispositivos.Periodo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.extras.ParserPeriodos;
import web.viewModels.RecomendacionVM;

public class RecomendacionesController extends VistaClienteController {
	
	public RecomendacionesController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request, Response response) {
		
		String periodoString = request.queryParams("periodo");
		
		ParserPeriodos parserPeriodos = new ParserPeriodos();
		
		Periodo periodo = parserPeriodos.parsear(periodoString);

		List<RecomendacionVM> recomendaciones = new AsesorDeUso()
				.recomendacionesPara(cliente.getDispositivos(), periodo)
				.stream()
				.map(recomendacion -> new RecomendacionVM(recomendacion, periodo))
				.collect(Collectors.toList());

		viewModel.put("recomendaciones", recomendaciones);
		viewModel.put("periodo", parserPeriodos.parsear(periodo));
		viewModel.put("fueEficiente", cliente.tieneHogarEficiente(periodo));
		viewModel.put("consumoTotal", cliente.consumoDe(periodo));
	}
	
	@Override
	protected String getUbicacionDelTemplate() {

		return "/vistas/privadas/cliente/recomendaciones.hbs";
	}
}