package dominio.importadorJson.parser.cliente;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public class DeserializadorFechaYHora implements JsonDeserializer<LocalDateTime> {

	DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		try {
			
			return LocalDateTime.parse(json.getAsString(), formateador);
		}
		catch (RuntimeException e) {

			throw new JsonSyntaxException(e.getMessage(), e);
		}
	}
}