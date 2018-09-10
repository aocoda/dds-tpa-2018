package repositorios;

import java.util.Optional;

import dominio.Categoria;
import dominio.TipoCategoria;

public class RepositorioCategorias extends RepositorioGenerico<Categoria> {
	
	public Optional<Categoria> getPorSubcategoria(String subcategoria) {
		
		return elementos
				.stream()
				.filter(categoria -> categoria.getSubtipoCategoria().equals(TipoCategoria.valueOf(subcategoria)))
				.findFirst();
	}
}