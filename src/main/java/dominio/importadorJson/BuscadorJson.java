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

public class BuscadorJson implements BuscadorRecursos {

	private String recurso;

	@Override
	public BuscadorRecursos buscarRecurso(String url) {
		
		try {
			
			recurso = new String(Files.readAllBytes(Paths.get(url)));
			
			return this;
		} 
		catch (IOException e) {
			
			throw new RecursoInvalidoException("Path invalido o archivo corrupto", e);
		}
	}

	@Override
	public String getAsString() {
		
		return recurso;
	}

	@Override
	public List<String> getAsList() {

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