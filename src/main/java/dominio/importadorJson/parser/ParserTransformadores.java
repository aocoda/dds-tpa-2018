package dominio.importadorJson.parser;

import com.google.gson.Gson;

import dominio.Transformador;

public class ParserTransformadores extends ParserJson<Transformador>{

	private Gson parser = new Gson();
	
	@Override
	protected Gson getGsonParser() {
		
		return parser;
	}

	@Override
	protected Class<Transformador> getParserClass() {
		
		return Transformador.class;
	}
}