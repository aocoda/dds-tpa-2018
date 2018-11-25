package web.controllers.privados.cliente;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.Cliente;
import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import dominio.dispositivos.Periodo;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;
import web.extras.ParserPeriodos;
import web.viewModels.DispositivoVM;

public class DispositivosController extends VistaClienteController implements TransactionalOps, WithGlobalEntityManager {
	
	public DispositivosController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request, Response response) {
		
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
	
	public Void agregar(Request request, Response response) {
		
		String tipoDispositivo = request.queryParams("tipoDispositivo");
		String nombreGenerico = request.queryParams("nombreGenerico");
		double consumoPorHora = Double.parseDouble(request.queryParams("consumoPorHora"));
		double horasDeUsoMinimo = Double.parseDouble(request.queryParams("horasDeUsoMinimo"));
		double horasDeUsoMaximo = Double.parseDouble(request.queryParams("horasDeUsoMaximo"));
		
		
		Cliente cliente = getCliente(request, response);
			
		withTransaction(() -> {

			if (tipoDispositivo.equals("dispositivoInteligente")) {
			
				cliente.registrarDispositivo(new DispositivoInteligente(nombreGenerico, consumoPorHora, horasDeUsoMinimo, horasDeUsoMaximo));
			}
			else {

				double horasEstimadasDeUsoPorDia = Double.parseDouble(request.queryParams("horasEstimadasDeUsoPorDia"));
				
				cliente.registrarDispositivo(new DispositivoEstandar(nombreGenerico, consumoPorHora, horasEstimadasDeUsoPorDia, horasDeUsoMinimo, horasDeUsoMaximo));
			}
		});

		response.redirect(request.uri());

		return null;
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/cliente/dispositivos.hbs";
	}
}