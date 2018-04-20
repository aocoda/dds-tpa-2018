package dominio;

abstract class Categoria {
	
	private double cargoFijoMensual;
	private double cargoVariable;

	public double estimadoAPagar(Cliente unCliente) {
		return  cargoFijoMensual + unCliente.consumoMensual() * cargoVariable;
	}

	abstract boolean leCorresponde(Cliente unCliente);
	
}

public object R1 extends Categoria {
	
	private double cargoFijoMensual = 18.76;
	private double cargoVariable = 0.644;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() <= 150;
	}
	
}

public object R2 extends Categoria {
	
	private double cargoFijoMensual = 35.32;
	private double cargoVariable = 0.644;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 150 && unCliente.consumoMensual() <= 325;
	}
	
}

public object R3 extends Categoria {
	
	private double cargoFijoMensual = 60.71;
	private double cargoVariable = 0.681;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 325 && unCliente.consumoMensual() <= 400;
	}
	
}

public object R4 extends Categoria {
	
	private double cargoFijoMensual = 71.74;
	private double cargoVariable = 0.738;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 400 && unCliente.consumoMensual() <= 450;
	}
	
}

public object R5 extends Categoria {
	
	private double cargoFijoMensual = 110.38;
	private double cargoVariable = 0.794;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 450 && unCliente.consumoMensual() <= 500;
	}
	
}

public object R6 extends Categoria {
	
	private double cargoFijoMensual = 220.75;
	private double cargoVariable = 0.832;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 500 && unCliente.consumoMensual() <= 600;
	}
	
}

public object R7 extends Categoria {
	
	private double cargoFijoMensual = 443.59;
	private double cargoVariable = 0.851;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 600 && unCliente.consumoMensual() <= 700;
	}
	
}

public object R8 extends Categoria {
	
	private double cargoFijoMensual = 545.96;
	private double cargoVariable = 0.851;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumoMensual() > 700 && unCliente.consumoMensual() <= 1400;
	}
	
}

public object R9 extends Categoria {
	
	private double cargoFijoMensual = 887.19;
	private double cargoVariable = 0.851;
	
	public boolean leCorresponde(Cliente unCliente) {
		return unCliente.consumo() > 1400;
	}
	
}

