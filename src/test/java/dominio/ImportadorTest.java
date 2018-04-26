package dominio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import dominio.importadorJson.BuscadorJson;
import dominio.importadorJson.ParserCategorias;
import dominio.importadorJson.ParserClientes;
import repositorios.RepositorioCategorias;
import repositorios.RepositorioClientes;

public class ImportadorTest {

	@Test
	public void test() throws IOException {
		
		String categorias = new BuscadorJson().buscarRecurso("C:\\\\users\\german\\desktop\\categorias1.json");
		String clientes = new BuscadorJson().buscarRecurso("C:\\\\users\\german\\desktop\\clientes1.json");
		
		RepositorioCategorias rcat = RepositorioCategorias.getInstancia();
		RepositorioClientes rclie = RepositorioClientes.getInstancia();
		
		new ParserCategorias().parsear(categorias).forEach(c -> {
			
			rcat.agregar(c);
		});
		
		new ParserClientes().parsear(clientes).forEach(c -> {
			
			rclie.agregar(c);
		});
		
		System.out.println(rcat.getAllInstances().size());
		System.out.println(rclie.getAllInstances().size());
		
		
		rclie.getAllInstances().forEach(c -> {
			
			System.out.println(c.getCategoria());
		});
						
	}

}
