package dominio.importadorJson.parser.cliente;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import dominio.Categoria;
import repositorios.RepositorioCategorias;

public class DeserializadorCategoria implements JsonDeserializer<Categoria> {

	private RepositorioCategorias repositorio;
	
	public DeserializadorCategoria(RepositorioCategorias repositorio) {
		
		this.repositorio = repositorio;
	}
	
	@Override
	public Categoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		return repositorio.getPorSubcategoria(json.getAsString()).orElse(null);
	}
}