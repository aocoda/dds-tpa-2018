package dominio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import dominio.excepciones.ParserException;
import dominio.importadorJson.parser.ParserCategorias;
import dominio.importadorJson.parser.ParserJson;

public class ParserCategoriasTest {

	private ParserJson<Categoria> parserCategorias = new ParserCategorias();
	
	@Test
	public void SiLaCategoriaJsonTieneTodosLosAtributos_SeCreaUnaCategoriaConEsosValores() {

		String categoriaTest = "{ " 
				+ "\"subtipoCategoria\": \"R1\"," 
				+ "\"cargoFijoMensual\" : 100,"
				+ "\"cargoVariable\" : 100," 
				+ "\"consumoDesde\": 0," 
				+ "\"consumoHasta\": 150" 
		+ " }";

		Categoria categoriaEsperada = new Categoria(TipoCategoria.R1, 100, 100, 0, 150);

		assertThat(parserCategorias.parsear(categoriaTest))
				.isEqualToComparingFieldByFieldRecursively(categoriaEsperada);
	}

	@Test
	public void SiLaCategoriaJsonNoTieneAlgunAtributo_SeCreaUnaCategoriaConValoresPorDefectoCeroONull() {

		String categoriaTest = "{ " 
				+ "\"cargoFijoMensual\" : 100," 
				+ "\"cargoVariable\" : 100," 
				+ "\"consumoDesde\": 0"
		+ " }";

		Categoria categoriaEsperada = new Categoria(null, 100, 100, 0, 0);

		assertThat(parserCategorias.parsear(categoriaTest))
				.isEqualToComparingFieldByFieldRecursively(categoriaEsperada);
	}

	@Test
	public void SiLaCategoriaJsonEstaMalFormadaEntonces_ArrojaParserException() {

		String categoriaTest = "{ " 
				+ "\"subtipoCategoria\": \"R1\"," 
				+ "\"cargoFijoMensual\" : 100,"
				+ "\"cargoVariable\" : 100," 
				+ "\"consumoDesde\": 0," 
				+ "\"consumoHasta\": 150";

		assertThatThrownBy(() -> parserCategorias.parsear(categoriaTest)).isInstanceOf(ParserException.class);
	}
}