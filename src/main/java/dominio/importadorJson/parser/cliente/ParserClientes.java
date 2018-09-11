package dominio.importadorJson.parser.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dominio.Categoria;
import dominio.Cliente;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;
import dominio.importadorJson.parser.ParserJson;
import repositorios.RepositorioCategorias;

public class ParserClientes extends ParserJson<Cliente> {

	private Gson parser;
	
	public ParserClientes(RepositorioCategorias repositorioCategorias) {
		
		this.parser = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new DeserializadorFecha())
				.registerTypeAdapter(Categoria.class, new DeserializadorCategoria(repositorioCategorias))
				.registerTypeAdapter(LocalDateTime.class, new DeserializadorFechaYHora())
				.registerTypeAdapter(EstadoDispositivo.class, new DeserializadorEstadoDispositivo())
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