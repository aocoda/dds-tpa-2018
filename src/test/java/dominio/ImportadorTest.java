package dominio;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import dominio.importadorJson.BuscadorJson;
import dominio.importadorJson.ParserCategorias;
import dominio.importadorJson.ParserClientes;
import repositorios.RepositorioCategorias;
import repositorios.RepositorioClientes;

public class ImportadorTest {

	@Test
	public void test() throws IOException {
		
		List<String> categorias = new BuscadorJson()
				.buscarRecurso("C:\\\\users\\ggallici\\desktop\\categorias1.json")
				.getAsList();
		
		List<String> clientes = new BuscadorJson()
				.buscarRecurso("C:\\\\users\\ggallici\\desktop\\clientes1.json")
				.getAsList();
		
		
		RepositorioCategorias rcat = RepositorioCategorias.getInstancia();
		RepositorioClientes rclie = RepositorioClientes.getInstancia();
		
		
		categorias.forEach(c -> {
			
			rcat.agregar(new ParserCategorias().parsear(c));
		});
		
		clientes.forEach(c -> {
			
			rclie.agregar(new ParserClientes().parsear(c));
		});
		
		
		System.out.println(rcat.getAllInstances().size());
		System.out.println(rclie.getAllInstances().size());
		
		
		rclie.getAllInstances().forEach(c -> {
			
			System.out.println(c.getCategoria());
		});				
	}
}