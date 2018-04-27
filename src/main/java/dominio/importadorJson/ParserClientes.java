package dominio.importadorJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import dominio.Categoria;
import dominio.Cliente;
import dominio.excepciones.ParserException;

public class ParserClientes  implements Parser<Cliente> {

	@Override
	public Cliente parsear(String recurso) {
		
		try {
			
			Gson gson = new GsonBuilder().registerTypeAdapter(Categoria.class, new DeserializadorCategoria()).create();

			return gson.fromJson(recurso, Cliente.class);
		}
		catch (JsonSyntaxException e) {

			throw new ParserException("Ocurrio un error durante el parseo: " + e.getMessage(), e.getCause());
		}
	}
}