package dominio.importadorJson;

import java.util.List;

public interface BuscadorRecursos {

	public BuscadorRecursos buscarRecurso(String url);
	
	public String getAsString();
	
	public List<String> getAsList();
}