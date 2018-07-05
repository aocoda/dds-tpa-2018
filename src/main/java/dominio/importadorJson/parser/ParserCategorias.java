package dominio.importadorJson.parser;

import com.google.gson.Gson;

import dominio.Categoria;

public class ParserCategorias extends ParserJson<Categoria> {
	
	private Gson parser = new Gson();

	@Override
	protected Gson getGsonParser() {
		
		return parser;
	}

	@Override
	protected Class<Categoria> getParserClass() {
		
		return Categoria.class;
	}
}