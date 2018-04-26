package dominio.importadorJson;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dominio.Categoria;

public class ParserCategorias implements Parser<Set<Categoria>> {
	
	@Override
	public Set<Categoria> parsear(String entidad) {
		
		return new Gson().fromJson(entidad, new TypeToken<HashSet<Categoria>>(){}.getType());  	
	}
}