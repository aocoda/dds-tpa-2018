package dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dominio.excepciones.RecursoInvalidoException;
import dominio.importadorJson.BuscadorJson;

public class BuscadorJsonTest {

	private BuscadorJson buscador = new BuscadorJson();
	
	@Test
	public void siElRecursoEsUnaListaJsonDeDosElementosBienFormados_GetAsList_DevuelveUnaListaStringDeDosElementos() {
		
		String recurso = "[ { \"nombre\": \"josesito\" } , { \"nombre\": \"pepito\" } ]";
		
		assertEquals(2, buscador.getAsList(recurso).size());
	}
	
	@Test
	public void siElRecursoEsUnaListaDeUnSoloElemento_GetAsList_DevuelveUnaListaStringUnElemento() {
		
		String recurso = "[ { \"nombre\": \"pepito\" } ]";
		
		assertEquals(1, buscador.getAsList(recurso).size());
	}
	
	@Test(expected = RecursoInvalidoException.class)
	public void siElRecursoEsUnaListaJsonMalFormada_GetAsList_ArrojaRecursoInvalidoException() {
		
		String recurso = "[ { \"nombre\": \"josesito\" } , { \"nombre\": \"pepito\" } ";
		
		buscador.getAsList(recurso);
	}
	
	@Test(expected = RecursoInvalidoException.class)
	public void siElRecursoNoEsUnaListaJson_GetAsList_ArrojaRecursoInvalidoException() {
		
		String recurso = "{ \"nombre\": \"josesito\" }";
		
		buscador.getAsList(recurso);
	}
}