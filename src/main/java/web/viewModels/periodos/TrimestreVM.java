package web.viewModels.periodos;

import java.util.List;

public class TrimestreVM {

	private List<MesVM> meses;
	
	public TrimestreVM(List<MesVM> meses) {

		this.meses = meses;
	}

	public int getAnio() {
		
		return meses.get(0).getAnio();
	}
	
	public List<MesVM> getMeses() {
		
		return meses;
	}
	
	public double getConsumo() {
		
		return meses.stream().mapToDouble(MesVM::getConsumo).sum();
	}
}