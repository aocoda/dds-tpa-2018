package dominio.importadorJson.parser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import dominio.Cliente;
import dominio.excepciones.ParserException;
import repositorios.RepositorioClientes;

public class DeserializadorClientes implements JsonDeserializer<Cliente>{

	private RepositorioClientes repositorio;

	public DeserializadorClientes(RepositorioClientes repositorio) {

		this.repositorio = repositorio;
	}

	@Override
	public Cliente deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		return repositorio
				.getPorNumeroDocumento(json.getAsInt())
				.orElseThrow(() -> new ParserException("El cliente: " + json.getAsInt() + "no existe en el repositorio", null));
	}
}