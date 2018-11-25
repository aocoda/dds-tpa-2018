package web;

import static spark.Spark.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import repositorios.RepositorioClientes;
import repositorios.RepositorioTransformadores;
import repositorios.RepositorioUsuarios;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.controllers.privados.PerfilController;
import web.controllers.privados.administrador.ClientesController;
import web.controllers.privados.administrador.TransformadoresController;
import web.controllers.privados.cliente.ConsumosController;
import web.controllers.privados.cliente.DispositivoNuevoController;
import web.controllers.privados.cliente.DispositivosController;
import web.controllers.privados.cliente.RecomendacionesController;
import web.controllers.publicos.HomeController;
import web.controllers.publicos.LoginController;
import web.extras.NotFoundException;
import web.extras.NotFoundUtils;

public class Aplicacion {

	public static void main(String[] args) {
		
		RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
		RepositorioClientes repositorioClientes = new RepositorioClientes();
		RepositorioTransformadores repositorioTransformadores = new RepositorioTransformadores();
		
		HomeController homeController = new HomeController();
		LoginController loginController = new LoginController(repositorioUsuarios);
		PerfilController perfilController = new PerfilController(repositorioUsuarios);
		TransformadoresController transformadoresController = new TransformadoresController(repositorioUsuarios, repositorioClientes, repositorioTransformadores);
		ClientesController clientesController = new ClientesController(repositorioUsuarios, repositorioClientes);
		DispositivosController dispositivosController = new DispositivosController(repositorioUsuarios, repositorioClientes);
		DispositivoNuevoController dispositivoNuevoController = new DispositivoNuevoController(repositorioUsuarios, repositorioClientes);
		ConsumosController consumosController = new ConsumosController(repositorioUsuarios, repositorioClientes);
		RecomendacionesController recomendacionesController = new RecomendacionesController(repositorioUsuarios, repositorioClientes);
		
		HandlebarsTemplateEngine templateEngine = new HandlebarsTemplateEngine();

		port(9000);
		
		//Publico
		get("/home", homeController::renderizarVista, templateEngine);	
		get("/login", loginController::renderizarVista, templateEngine);
		post("/login", loginController::login);
		post("/logout", loginController::logout);
		
		
		//Administrador-Cliente
		get("/perfil", perfilController::renderizarVista, templateEngine);
		
		
		//Administrador
		get("/transformadores", transformadoresController::renderizarVista, templateEngine);
		get("/clientes", clientesController::renderizarVista, templateEngine);
		get("/clientes/:id/perfil", perfilController::renderizarVista, templateEngine);
		get("/clientes/:id/dispositivos", dispositivosController::renderizarVista, templateEngine);
		get("/clientes/:id/dispositivos/nuevo", dispositivoNuevoController::renderizarVista, templateEngine);
		post("/clientes/:id/dispositivos", dispositivosController::agregar);
		get("/clientes/:id/consumos", consumosController::renderizarVista, templateEngine);	
		get("/clientes/:id/recomendaciones", recomendacionesController::renderizarVista, templateEngine);
		
		
		//Cliente
		get("/dispositivos", dispositivosController::renderizarVista, templateEngine);
		get("/dispositivos/nuevo", dispositivoNuevoController::renderizarVista, templateEngine);
		post("/dispositivos", dispositivosController::agregar);
		get("/consumos", consumosController::renderizarVista, templateEngine);
		get("/recomendaciones", recomendacionesController::renderizarVista, templateEngine);
		
		
		//Extras
		get("*", (request, response) -> NotFoundUtils.generarVista(response));
		exception(NotFoundException.class, (exception, request, response) -> NotFoundUtils.generarVista(response));
		after((request, response) -> PerThreadEntityManagers.getEntityManager().close());
	}
}