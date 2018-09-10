package dominio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import dominio.geoposicionamiento.Area;
import dominio.geoposicionamiento.Coordenada;
import dominio.importadorJson.parser.ParserJson;
import dominio.importadorJson.parser.ParserTransformadores;
import dominio.importadorJson.parser.ParserZonas;

public class ParserZonasTranformadoresTest {
	
	private ParserJson<Zona> parserZonas = new ParserZonas();
	private ParserJson<Transformador> parserTransformadores = new ParserTransformadores();
	
	@Test
	public void parseandoUnaZona() {
		
		String zonaTest = "{"
				+ "\"nombre\": \"zona1\","
				+ "\"area\":"
					+ "{"
						+ "\"coordenada\":"
							+ "{"
								+ "\"latitud\": 1,"
								+ "\"longitud\": 2"
							+ "},"
							+ "\"radio\": 5"
					+ "}"
				+ "}";
		
		Zona zonaObtenida = parserZonas.parsear(zonaTest);
		Zona zonaEsperada = new Zona("zona1", new Area(new Coordenada(1, 2), 5));
		
		assertThat(zonaObtenida).isEqualToComparingFieldByFieldRecursively(zonaEsperada);
	}
	
	@Test
	public void parseandoUnTransformador() {
		

		String transformadorTest = "{"
				+ "\"nombre\": \"transformador1\","
				+ "\"coordenada\":"
					+ "{"
						+ "\"latitud\": 2,"
						+ "\"longitud\": 1"
					+ "}"
				+ "}";

		Transformador transformadorObtenido = parserTransformadores.parsear(transformadorTest);
		Transformador transformadorEsperado  = new Transformador("transformador1", new Coordenada(2, 1));
		
		assertThat(transformadorObtenido).isEqualToComparingFieldByFieldRecursively(transformadorEsperado);
	}
}