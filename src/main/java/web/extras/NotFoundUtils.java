package web.extras;

import org.eclipse.jetty.http.HttpStatus;

import spark.ModelAndView;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public interface NotFoundUtils {

	public static String generarVista(Response response) {
		
		response.status(HttpStatus.NOT_FOUND_404);
		
		return new HandlebarsTemplateEngine().render(new ModelAndView(null, "/vistas/publicas/notFound.hbs"));
	}
}