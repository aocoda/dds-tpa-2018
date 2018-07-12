package dominio.importadorJson.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Cliente;
import dominio.Zona;
import repositorios.RepositorioClientes;

public class ParserZonas extends ParserJson<Zona> {

private Gson parser;
	
	public ParserZonas(RepositorioClientes repositorioClientes) {
		
		this.parser = new GsonBuilder()
				.registerTypeAdapter(Cliente.class, new DeserializadorClientes(repositorioClientes))
				.create();
	}
	
	@Override
	protected Gson getGsonParser() {
		
		return parser;
	}

	@Override
	protected Class<Zona> getParserClass() {
		
		return Zona.class;
	}
}