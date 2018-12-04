package web;

import static spark.Spark.*;

import org.eclipse.jetty.http.HttpStatus;

import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.controllers.privados.administrador.ClientesController;
import web.controllers.privados.administrador.TransformadoresController;
import web.controllers.privados.cliente.ConsumosController;
import web.controllers.privados.cliente.DispositivoNuevoController;
import web.controllers.privados.cliente.DispositivosController;
import web.controllers.privados.cliente.PerfilController;
import web.controllers.privados.cliente.RecomendacionesController;
import web.controllers.publicos.HomeController;
import web.controllers.publicos.LoginController;
import web.extras.NotFoundException;
import web.middlewares.AutenticacionMiddleware;

public class Aplicacion {

	public static void main(String[] args) {
		
		RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
		RepositorioClientes repositorioClientes = new RepositorioClientes();
		RepositorioTransformadores repositorioTransformadores = new RepositorioTransformadores();
		
		AutenticacionMiddleware autenticacionMidleware = new AutenticacionMiddleware(repositorioUsuarios);
		
		HomeController homeController = new HomeController(repositorioUsuarios);
		LoginController loginController = new LoginController(repositorioUsuarios);
		PerfilController perfilController = new PerfilController(repositorioUsuarios, repositorioClientes, repositorioTransformadores);
		TransformadoresController transformadoresController = new TransformadoresController(repositorioUsuarios, repositorioClientes, repositorioTransformadores);
		ClientesController clientesController = new ClientesController(repositorioUsuarios, repositorioClientes);
		DispositivosController dispositivosController = new DispositivosController(repositorioUsuarios, repositorioClientes);
		DispositivoNuevoController dispositivoNuevoController = new DispositivoNuevoController(repositorioUsuarios, repositorioClientes);
		ConsumosController consumosController = new ConsumosController(repositorioUsuarios, repositorioClientes);
		RecomendacionesController recomendacionesController = new RecomendacionesController(repositorioUsuarios, repositorioClientes);
		
		HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();
		
		staticFileLocation("/public");

		port(9000);
		
		//Publico
		get("/", (request, response) -> { 
			
			response.redirect("/home");
			
			return null;
		});
		get("/unimplemented", (request, response) -> new ModelAndView(null, "/vistas/publicas/unimplemented.hbs"), templateEngine);
		get("/home", homeController::renderizarVista, templateEngine);
		get("/login", loginController::renderizarVista, templateEngine);
		post("/login", loginController::login);
		post("/logout", loginController::logout);
		
		
		//Administrador
		path("/clientes", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeAdministrador);
			before("/*", autenticacionMidleware::asegurarPermisosDeAdministrador);	
			get("", clientesController::renderizarVista, templateEngine);
			get("/:id/perfil", perfilController::renderizarVista, templateEngine);
			path("/:id/dispositivos", () -> {
				get("", dispositivosController::renderizarVista, templateEngine);
				get("/nuevo", dispositivoNuevoController::renderizarVista, templateEngine);
				post("", dispositivoNuevoController::agregar);
			});
			get("/:id/consumos", consumosController::renderizarVista, templateEngine);	
			get("/:id/recomendaciones", recomendacionesController::renderizarVista, templateEngine);
			post(":id/apagadoAutomatico", perfilController::permutarApagadoAutomatico);
		});
		path("/transformadores", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeAdministrador);
			get("", transformadoresController::renderizarVista, templateEngine);
		});
		
		
		//Cliente
		path("/perfil", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeCliente);
			get("", perfilController::renderizarVista, templateEngine);			
		});
		path("/dispositivos", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeCliente);
			before("/*", autenticacionMidleware::asegurarPermisosDeCliente);
			get("", dispositivosController::renderizarVista, templateEngine);
			get("/nuevo", dispositivoNuevoController::renderizarVista, templateEngine);
			post("", dispositivoNuevoController::agregar);
		});
		path("/consumos", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeCliente);
			get("", consumosController::renderizarVista, templateEngine);
		});
		path("/recomendaciones", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeCliente);
			get("", recomendacionesController::renderizarVista, templateEngine);
		});
		path("/apagadoAutomatico", () -> {
			before("", autenticacionMidleware::asegurarPermisosDeCliente);
			post("", perfilController::permutarApagadoAutomatico);
		});
		
		
		//Extras
		get("*", (request, response) -> {
			
			response.status(HttpStatus.NOT_FOUND_404);
			
			return new ModelAndView(null, "/vistas/publicas/notFound.hbs");
		}, templateEngine);
		exception(NotFoundException.class, (exception, request, response) -> {
			
			String vistaRenderizada = templateEngine.render(new ModelAndView(null, "/vistas/publicas/notFound.hbs"));
			
			notFound(vistaRenderizada);
		});
	}
}