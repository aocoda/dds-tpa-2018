package dominio.importadorJson.parser.cliente;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import dominio.dispositivos.inteligentes.estados.Ahorro;
import dominio.dispositivos.inteligentes.estados.Apagado;
import dominio.dispositivos.inteligentes.estados.Encendido;
import dominio.dispositivos.inteligentes.estados.EstadoDispositivo;

public class DeserializadorEstadoDispositivo implements JsonDeserializer<EstadoDispositivo>{

	@Override
	public EstadoDispositivo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		
		String nombreClase = json.getAsString();
		
		return nombreClase.equals("Apagado") ? new Apagado() 
			 : nombreClase.equals("Encendido") ? new Encendido() 
			 : new Ahorro();
	}
}