package dominio.importadorJson.parser;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import dominio.dispositivos.DispositivoInteligente;

public class DeserializadorDispositivosInteligentes implements JsonDeserializer<DispositivoInteligente> {

	@Override
	public DispositivoInteligente deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		String nombreClase = json.getAsJsonObject().get("tipo").getAsString();
		
		try {
			
			Class<?> clase = Class.forName(nombreClase);
			
			return context.deserialize(json, clase);
		} 
		catch (ClassNotFoundException | ClassCastException | NullPointerException e) {
			
			throw new JsonParseException(e.getMessage(), e.getCause());
		}
	}
}