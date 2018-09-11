package repositorios;

import dominio.Cliente;
import dominio.dispositivos.Periodo;

public class RepositorioClientes extends RepositorioGenerico<Cliente> {

	public void ejecutarReglasQueCorrespondan() {
		
		elementos.forEach(cliente -> cliente.ejecutarReglasQueCorrespondan());
	}
	
	public void ejecutarApagadoAutomaticoPorConsumo(Periodo unPeriodo) {
		
		elementos
		.stream()
		.filter(cliente -> cliente.tieneApagadoAutomaticoActivado())
		.forEach(cliente -> cliente.ejecutarApagadoPorConsumo(unPeriodo));
	}
}