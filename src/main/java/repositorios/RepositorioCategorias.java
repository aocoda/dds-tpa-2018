package repositorios;

import dominio.Categoria;

public class RepositorioCategorias extends RepositorioGenerico<Categoria>{

	private static RepositorioCategorias instancia;

	public static RepositorioCategorias getInstancia() 
	{
		if(instancia == null) {
            
			instancia = new RepositorioCategorias();
        }
		return instancia;
    }
}