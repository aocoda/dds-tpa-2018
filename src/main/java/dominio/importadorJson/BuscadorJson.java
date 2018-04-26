package dominio.importadorJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BuscadorJson implements BuscadorRecursos {

	private String recurso;

	@Override
	public BuscadorRecursos buscarRecurso(String url) {
		
		try {
			
			recurso = new String(Files.readAllBytes(Paths.get(url)));
			
			return this;
		} 
		catch (IOException e) {
			
			throw new RuntimeException();
		}
	}

	@Override
	public String getAsString() {
		
		return recurso;
	}

	@Override
	public List<String> getAsList() {
		
		return null;
	}
}