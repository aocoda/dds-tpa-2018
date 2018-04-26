package dominio.importadorJson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import dominio.Categoria;
import repositorios.RepositorioCategorias;

public class DeserializadorCategoria implements JsonDeserializer<Categoria> {

	@Override
	public Categoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		return RepositorioCategorias
				.getInstancia()
				.getPorSubcategoria(json.getAsString())
				.orElse(null);
	}
}