package dominio.importadorJson;

public interface Parser<T> {

	public T parsear(String recurso);
}
