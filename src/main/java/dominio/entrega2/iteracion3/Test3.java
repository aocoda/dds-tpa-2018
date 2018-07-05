package dominio.entrega2.iteracion3;

import java.util.Arrays;

import dominio.mocks.DispositivoMock;
import dominio.reglas.actuadores.Actuador;
import dominio.reglas.reglas.Regla;

public class Test3 {

	public static void main(String[] args) {
		
		DispositivoMock dispositivo = new DispositivoMock("dispositivoTest", 10);
		
		Actuador<DispositivoMock> apagador = d -> d.apagar();
		
		
		//CONDICION
		/*
		Esto es lo que hay que hacer andar. El problema es el siguiente:
		La condicion necesita conocer ciertas dependencias que no sabemos bien como obtener:
			-asesor
			-restricciones del asesor
			-dispositivo sobre el cual se mide si supera o no las horas optimas
			-coleccion de dispositivos (son necesarios para el calculo del simplex, pero no tiene ningun sentido en la condicion)
			-periodo para calculo del simplex
		
		El dispositivo y el periodo, puedo asumir que ya los tengo, dado que la regla necesita un dispositivo para funcionar y que el periodo siempre va a ser un mes
		 */
		
		CondicionSimplex condicion = new CondicionSimplex();

		
		Regla<DispositivoMock> regla = new Regla<>(Arrays.asList(condicion), Arrays.asList(apagador), dispositivo);
	}
}