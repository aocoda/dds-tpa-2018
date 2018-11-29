package web.controllers.privados.cliente;

import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.Cliente;
import dominio.dispositivos.DispositivoEstandar;
import dominio.dispositivos.DispositivoInteligente;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import spark.Response;


public class DispositivoNuevoController extends VistaClienteController implements TransactionalOps, WithGlobalEntityManager {

	public DispositivoNuevoController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}
	
	public Void agregar(Request request, Response response) {
		
		String tipoDispositivo = request.queryParams("tipoDispositivo");
		String nombreGenerico = request.queryParams("nombreGenerico");
		double consumoPorHora = Double.parseDouble(request.queryParams("consumoPorHora"));
		double horasDeUsoMinimo = Double.parseDouble(request.queryParams("horasDeUsoMinimo"));
		double horasDeUsoMaximo = Double.parseDouble(request.queryParams("horasDeUsoMaximo"));
		
		
		Cliente cliente = getCliente(request);
			
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
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request) {
	
		viewModel.put("url_agregar_dispositivo", request.uri().replace("/nuevo", ""));
	}

	@Override
	protected String getUbicacionDelTemplate() {
		
		return "/vistas/privadas/cliente/dispositivoNuevo.hbs";
	}
}