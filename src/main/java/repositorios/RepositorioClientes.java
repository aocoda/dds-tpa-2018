package repositorios;

import dominio.Cliente;
import dominio.dispositivos.Periodo;

public class RepositorioClientes extends RepositorioGenerico<Cliente> {

	public void ejecutarReglasQueCorrespondan() {
		
		getAllInstances().forEach(cliente -> cliente.ejecutarReglasQueCorrespondan());
	}
	
	public void ejecutarApagadoAutomaticoPorConsumo(Periodo unPeriodo) {
		
		getAllInstances()
		.stream()
		.filter(cliente -> cliente.tieneApagadoAutomaticoActivado())
		.forEach(cliente -> cliente.ejecutarApagadoPorConsumo(unPeriodo));
	}

	@Override
	protected Class<Cliente> getClase() {
		
		return Cliente.class;
	}
}