package dominio.importadorJson;

import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Categoria;
import dominio.Cliente;
import dominio.dispositivos.DispositivoInteligente;
import repositorios.RepositorioCategorias;

public class ParserClientes extends ParserJson<Cliente> {

	private Gson parser;
	
	public ParserClientes(RepositorioCategorias repositorioCategorias) {
		
		this.parser = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new DeserializadorFecha())
				.registerTypeAdapter(Categoria.class, new DeserializadorCategoria(repositorioCategorias))
				.registerTypeAdapter(DispositivoInteligente.class, new DeserializadorDispositivosInteligentes())
				.create();
	}

	@Override
	protected Gson getGsonParser() {
		
		return parser;
	}

	@Override
	protected Class<Cliente> getParserClass() {
		
		return Cliente.class;
	}
}