package dominio.importadorJson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuscadorJson implements BuscadorRecursos {

	@Override
	public String buscarRecurso(String url) {
		
		try {
			
			return new String(Files.readAllBytes(Paths.get(url)));
		} 
		catch (IOException e) {
			
			throw new RuntimeException();
		}
	}

}
