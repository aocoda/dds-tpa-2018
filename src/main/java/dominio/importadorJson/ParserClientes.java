package dominio.importadorJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Categoria;
import dominio.Cliente;

public class ParserClientes  implements Parser<Cliente> {

	@Override
	public Cliente parsear(String recurso) {

		Gson gson = new GsonBuilder().registerTypeAdapter(Categoria.class, new DeserializadorCategoria()).create();

		return gson.fromJson(recurso, Cliente.class);
	}
}