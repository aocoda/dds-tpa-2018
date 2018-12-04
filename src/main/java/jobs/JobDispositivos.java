package jobs;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dominio.dispositivos.PeriodoUtils;
import repositorios.RepositorioClientes;

public class JobDispositivos implements Job, TransactionalOps, WithGlobalEntityManager {

    private RepositorioClientes repositorioClientes = new RepositorioClientes();

	@Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	
		System.out.println("Job iniciado a las: " + LocalDateTime.now());

		withTransaction(() -> {
    		
    		repositorioClientes.ejecutarReglasQueCorrespondan();
    		
    		repositorioClientes.ejecutarApagadoAutomaticoPorConsumo(PeriodoUtils.delMesActual());   
    	});
    	
    	PerThreadEntityManagers.closeEntityManager();
    }
}