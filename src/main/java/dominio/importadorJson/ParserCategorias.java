package dominio.importadorJson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dominio.Categoria;
import dominio.excepciones.ParserException;

public class ParserCategorias implements Parser<Categoria> {
	
	@Override
	public Categoria parsear(String recurso) {
		
		try {
			
			return new Gson().fromJson(recurso, Categoria.class);
		}
		catch(JsonSyntaxException e) {
			
			throw new ParserException("Ocurrio un error durante el parseo: " + e.getMessage(), e.getCause());
		}
	}
}