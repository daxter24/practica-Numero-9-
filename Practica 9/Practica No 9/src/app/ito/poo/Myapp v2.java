//equipo 1
//equipo 1

package app.ito.poo;

import java.awt.HeadlessException;
import Clases.ito.poo.CuentasBanco;
import excepcion.ito.poo.CuentaExistente;
import excepcion.ito.poo.BorrarCuenta;
import excepcion.ito.poo.Deposito;
import excepcion.ito.poo.NumeCuenta;
import excepcion.ito.poo.Retiro;
import excepcion.ito.poo.Saldo;

public class Myapp2 {
	
	static CuentasBanco c;
	public static void main(String[] args) throws HeadlessException, NumeCuenta, Saldo, Retiro, Deposito, CuentaExistente, BorrarCuenta {
		Aplicacion2.menu();
	}

}