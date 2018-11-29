package web.controllers.privados.cliente;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.Cliente;
import dominio.dispositivos.Periodo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import web.extras.ParserPeriodos;
import web.viewModels.DispositivoVM;

public class DispositivosController extends VistaClienteController {
	
	public DispositivosController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request) {
		
		String periodoString = request.queryParams("periodo");
		
		ParserPeriodos parserPeriodos = new ParserPeriodos();
		
		Periodo periodo = parserPeriodos.parsear(periodoString);
		
		List<DispositivoVM> dispositivos = cliente
				.getDispositivos()
				.stream()
				.map(dispositivo -> new DispositivoVM(dispositivo, periodo))
				.collect(Collectors.toList());	
		
		viewModel.put("dispositivos", dispositivos);
		viewModel.put("periodo", parserPeriodos.parsear(periodo));
		viewModel.put("consumoTotal", cliente.consumoDe(periodo));
		viewModel.put("url_formulario_dispositivoNuevo", request.uri() + "/nuevo");
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/cliente/dispositivos.hbs";
	}
}