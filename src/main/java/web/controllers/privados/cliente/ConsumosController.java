package web.controllers.privados.cliente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import dominio.Cliente;
import dominio.dispositivos.Periodo;
import dominio.dispositivos.PeriodoUtils;
import repositorios.RepositorioClientes;
import repositorios.RepositorioUsuarios;
import spark.Request;
import web.extras.ParserPeriodos;
import web.viewModels.periodos.MesVM;
import web.viewModels.periodos.TrimestreVM;

public class ConsumosController extends VistaClienteController {

	public ConsumosController(RepositorioUsuarios repositorioUsuarios, RepositorioClientes repositorioClientes) {
		
		super(repositorioUsuarios, repositorioClientes);
	}

	@Override
	protected void agregarDatosDelCliente(Map<String, Object> viewModel, Cliente cliente, Request request) {

		String periodoString = request.queryParams("periodo");
		
		ParserPeriodos parserPeriodos = new ParserPeriodos();
		
		Periodo periodo = parserPeriodos.parsear(periodoString);
		
		viewModel.put("periodo", parserPeriodos.parsear(periodo));
		viewModel.put("consumoDelPeriodo", cliente.consumoDe(periodo));
		
		
		Periodo enero = PeriodoUtils.delMes(1);
		Periodo febrero = PeriodoUtils.delMes(2);
		Periodo marzo = PeriodoUtils.delMes(3);
		Periodo abril = PeriodoUtils.delMes(4);
		Periodo mayo = PeriodoUtils.delMes(5);
		Periodo junio = PeriodoUtils.delMes(6);
		Periodo julio = PeriodoUtils.delMes(7);
		Periodo agosto = PeriodoUtils.delMes(8);
		Periodo septiembre = PeriodoUtils.delMes(9);
		Periodo octubre = PeriodoUtils.delMes(10);
		Periodo noviembre = PeriodoUtils.delMes(11);
		Periodo diciembre = PeriodoUtils.delMes(12);
		
		MesVM eneroCalculado = new MesVM(enero, cliente.consumoDe(enero));
		MesVM febreroCalculado = new MesVM(febrero, cliente.consumoDe(febrero));
		MesVM marzoCalculado = new MesVM(marzo, cliente.consumoDe(marzo));
		MesVM abrilCalculado = new MesVM(abril, cliente.consumoDe(abril));
		MesVM mayoCalculado = new MesVM(mayo, cliente.consumoDe(mayo));
		MesVM junioCalculado = new MesVM(junio, cliente.consumoDe(junio));
		MesVM julioCalculado = new MesVM(julio, cliente.consumoDe(julio));
		MesVM agostoCalculado = new MesVM(agosto, cliente.consumoDe(agosto));
		MesVM septiembreCalculado = new MesVM(septiembre, cliente.consumoDe(septiembre));
		MesVM octubreCalculado = new MesVM(octubre, cliente.consumoDe(octubre));
		MesVM noviembreCalculado = new MesVM(noviembre, cliente.consumoDe(noviembre));
		MesVM diciembreCalculado = new MesVM(diciembre, cliente.consumoDe(diciembre));
		
		List<TrimestreVM> trimestresCalculados = new ArrayList<>();
		
		trimestresCalculados.add(new TrimestreVM(Arrays.asList(eneroCalculado, febreroCalculado, marzoCalculado)));
		trimestresCalculados.add(new TrimestreVM(Arrays.asList(abrilCalculado, mayoCalculado, junioCalculado)));
		trimestresCalculados.add(new TrimestreVM(Arrays.asList(julioCalculado, agostoCalculado, septiembreCalculado)));
		trimestresCalculados.add(new TrimestreVM(Arrays.asList(octubreCalculado, noviembreCalculado, diciembreCalculado)));
		
		viewModel.put("trimestres", trimestresCalculados);
	}

	@Override
	protected String getUbicacionDelTemplate() {
	
		return "/vistas/privadas/cliente/consumos.hbs";
	}
}