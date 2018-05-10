package dominio.importadorJson;

import java.util.Collection;
import java.util.stream.Collectors;

import repositorios.RepositorioGenerico;

public class ImportadorJson {

	public <T> void importar(String url, ParserJson<T> parser, RepositorioGenerico<T> repositorio) {
		
		Collection<String> datos = buscarRecurso(url);
		
		Collection<T> datosParseados = parsear(parser, datos);
		
		agregar(datosParseados, repositorio);
	}

	
	private Collection<String> buscarRecurso(String url) {
		
		return new BuscadorJson().buscarRecurso(url);
	}
	
	private <T> Collection<T> parsear(ParserJson<T> parser, Collection<String> datos) {
		
		return datos.stream().map(dato -> parser.parsear(dato)).collect(Collectors.toSet());
	}
	
	private <T> void agregar(Collection<T> elementos, RepositorioGenerico<T> repositorio) {
		
		elementos.forEach(elemento -> repositorio.agregar(elemento));
	}
}