package dominio.importadorJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import dominio.excepciones.RecursoInvalidoException;

public class BuscadorJson {

	public List<String> buscarRecurso(String url) {
		
		try {
			
			return getAsList(new String(Files.readAllBytes(Paths.get(url))));
		} 
		catch (IOException e) {
			
			throw new RecursoInvalidoException("Path invalido o archivo corrupto", e);
		}
	}

	/*
	 * Método solo para test
	 */
	public List<String> getAsList(String recurso) {

		try {
			
			return StreamSupport
					.stream(new JsonParser().parse(recurso).getAsJsonArray().spliterator(), false)
					.map(JsonElement::toString)
					.collect(Collectors.toList());
		} 
		catch (JsonSyntaxException | IllegalStateException e) {

			throw new RecursoInvalidoException("El recurso json no es del tipo lista", e);
		}
	}
}