package repositorios;

import java.util.Optional;

import dominio.Categoria;
import dominio.SubtipoCategoria;

public class RepositorioCategorias extends RepositorioGenerico<Categoria>{

	private static RepositorioCategorias instancia;

	public static RepositorioCategorias getInstancia() 
	{
		if(instancia == null) {
            
			instancia = new RepositorioCategorias();
        }
		return instancia;
    }
	
	public Optional<Categoria> getPorSubcategoria(String subcategoria) {
		
		return elementos
				.stream()
				.filter(categoria -> categoria.getSubtipoCategoria().equals(SubtipoCategoria.valueOf(subcategoria)))
				.findFirst();
	}
}