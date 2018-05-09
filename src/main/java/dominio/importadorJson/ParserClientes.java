package dominio.importadorJson;

import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import dominio.Categoria;
import dominio.Cliente;
import dominio.excepciones.ParserException;
import repositorios.RepositorioCategorias;

public class ParserClientes  implements Parser<Cliente> {

	private RepositorioCategorias repositorioCategorias;
	
	public ParserClientes(RepositorioCategorias repositorioCategorias) {
		
		this.repositorioCategorias = repositorioCategorias;
	}

	@Override
	public Cliente parsear(String recurso) {
		
		try {
			
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new DeserializadorFecha())
					.registerTypeAdapter(Categoria.class, new DeserializadorCategoria(repositorioCategorias))
					.create();

			return gson.fromJson(recurso, Cliente.class);
		}
		catch (JsonSyntaxException e) {

			throw new ParserException("Ocurrio un error durante el parseo: " + e.getMessage(), e.getCause());
		}
	}
}