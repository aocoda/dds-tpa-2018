package dominio.importadorJson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dominio.excepciones.ParserException;

public abstract class ParserJson<T> {

	public T parsear(String recurso) {
		
		try {
			
			return getGsonParser().fromJson(recurso, getParserClass());
		}
		catch(JsonSyntaxException e) {
			
			throw new ParserException("Ocurrio un error durante el parseo: " + e.getMessage(), e.getCause());
		}
	}
	
	protected abstract Gson getGsonParser();
	
	protected abstract Class<T> getParserClass();
}