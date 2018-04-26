package dominio.importadorJson;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dominio.Categoria;
import dominio.Cliente;

public class ParserClientes  implements Parser<Set<Cliente>> {

	@Override
	public Set<Cliente> parsear(String recurso) {

		Gson gson = new GsonBuilder().registerTypeAdapter(Categoria.class, new DeserializadorCategoria()).create();

		return gson.fromJson(recurso, new TypeToken<HashSet<Cliente>>(){}.getType());
	}
}