package dominio.reglas.condiciones;

public enum Relacion {

	MENOR_A {
		
		@Override
		public boolean aplicarCon(double unValor, double otroValor) {
			
			return otroValor < unValor;
		}
	}
	,
	MAYOR_A {
		
		@Override
		public boolean aplicarCon(double unValor, double otroValor) {
			
			return otroValor > unValor;
		}
	}
	,
	IGUAL_A {
		
		@Override
		public boolean aplicarCon(double unValor, double otroValor) {
			
			return otroValor == unValor;
		}
	}
	
	;
	
	public abstract boolean aplicarCon(double unValor, double otroValor);
}