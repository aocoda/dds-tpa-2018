package dominio.importadorJson;

import com.google.gson.Gson;

import dominio.Categoria;

public class ParserCategorias implements Parser<Categoria> {
	
	@Override
	public Categoria parsear(String entidad) {
		
		return new Gson().fromJson(entidad, Categoria.class);  	
	}
}