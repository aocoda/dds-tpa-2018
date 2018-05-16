package dominio.importadorJson;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

public class DeserializadorFecha implements JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		try {
			
			return LocalDate.parse(json.getAsString());
		}
		catch (RuntimeException e) {

			throw new JsonSyntaxException(e.getMessage(), e);
		}
	}
}