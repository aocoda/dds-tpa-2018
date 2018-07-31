package repositorios;

import java.util.Optional;

import dominio.Cliente;
import dominio.dispositivos.Periodo;

public class RepositorioClientes extends RepositorioGenerico<Cliente> {

	public void ejecutarReglasQueCorrespondan() {
		
		elementos.forEach(cliente -> cliente.ejecutarReglasQueCorrespondan());
	}

	public Optional<Cliente> getPorNumeroDocumento(int numeroDocumento) {
		
		return elementos
				.stream()
				.filter(cliente -> cliente.getNumeroDocumento() == numeroDocumento)
				.findFirst();
	}
	
	public void ejecutarApagadoAutomaticoPorConsumo(Periodo unPeriodo) {
		
		elementos
		.stream()
		.filter(cliente -> cliente.tieneApagadoAutomaticoActivado())
		.forEach(cliente -> cliente.ejecutarApagadoPorConsumo(unPeriodo));
	}
}