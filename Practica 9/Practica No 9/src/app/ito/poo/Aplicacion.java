//equipo 1


package app.ito.poo;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import Clases.ito.poo.CuentaBancaria;
import Clases.ito.poo.CuentasdeBanco;
import Escritor.eArchivo;
import Escritor.lArchvo;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepcion.ito.poo.CuentaExistente;
import excepcion.ito.poo.BorrarCuenta;
import excepcion.ito.poo.Deposito;
import excepcion.ito.poo.NumeCuenta;
import excepcion.ito.poo.Retiro;
import excepcion.ito.poo.Saldo;

public class Aplicacion {

	
	static miCuentaBancaria e;
	
	 static CuentasdeBanco c;
	   static lArchvo archivo2;
	   static eArchivo archivo;
	
	
	static void menu() throws NumeroCuenta, Saldo, Retiro, Deposito, HeadlessException, CuentaYaExistente, BorrarCuenta, FileNotFoundException {
		Comenzar();
		
		  IniciarP();
		  
		    final JPanel panel=new JPanel();
		
		boolean error=true;
		
		 while(error) {
			
		 try {
		  boolean ciclo=true;
		
		 int respuesta=0;
		
		   while(ciclo) {
			
		String opciones="¿Qué desea hacer?:\n 1)Agregar una nueva cuenta\n 2)Mostrar las cuentas registradas\n 3)Hacer un depósito a una cuenta existente\n"
				+  " 4)retirar\n 5)Eliminar una cuenta \n 6)Consultar su saldo\n 7)finalizar";
		
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		switch(respuesta) {
		
		case 1:agregarCuenta();break;
		
		case 2:ensenarCuenta();break;
		
		case 3:depositar();break;
		
		case 4:retirar();break;
		
		case 5:eliminar();break;
		
		case 6:consultar();break;
		
		case 7:grabarRegistro();ciclo=false;error=false;break;
		
		default:JOptionPane.showMessageDialog(null,"Ingrese aquí la opción deseada");
		  }
		}
		}catch(NumeCuenta e){
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(Saldo e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(Deposito e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(Retiro e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(CuentaExistente e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}catch(BorrarCuenta e) {
			JOptionPane.showMessageDialog(panel,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		}
	}
	
	static CuentaBancaria capturarCuenta() throws NumeCuenta,Saldo {
		CuentaBancaria n=new CuentaBancaria();
		long l;String fecha,nombre;float saldo;
		l=Long.parseLong(JOptionPane.showInputDialog("Número de cuenta deseado"));
		nombre=JOptionPane.showInputDialog("Titular:");
		saldo=Float.parseFloat(JOptionPane.showInputDialog("Saldo"));
		fecha=JOptionPane.showInputDialog("Fecha de apertura(año-mes-día):");
		n.setNumeCuenta(l);
		n.setNomCliente(nombre);
		n.setSaldo(saldo);
		n.setFechaApertura(LocalDate.parse(fecha));
		return n;
	}
	
	static void Comenzar() {
		
		c=new CuentasBanco();
	}
	
	
	
	
	
	static void agregarCuenta() throws NumeCuenta, Saldo, HeadlessException, CuentaExistente {
		CuentaBancaria nueva;
		
		nueva=capturarCuenta();
		
		c.CuentaExistente(nueva);
		
		c.addItem(nueva);
		
	    JOptionPane.showMessageDialog(null,"Cuenta agregada con exito!");
	      JOptionPane.showMessageDialog(null,"Se agregó correctamente");
	      
			if(c.isFull())
				c.crecerArreglo();
	}
	
	static void hacerDeposito() throws Saldo, Deposito {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Todavía no hay ninguna cuenta registrada");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Elija la cuenta a la que desea hacer el depósito\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("¿Cuánto desea ingresar?"));
		    c.Deposito(cantidad);
		    c.getItem(pos-1).setSaldoActualizado(c.getItem(pos-1).getSaldo()+cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"No necesita tikect, su depósito se realizó con éxito uwu");
		    bandera=false;
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"Cuenta no registrada en el sistema");
			}
		}
	}
	
	static void mostrarCuentas() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Cuenta inexistente!");
		else {
		String cuentas="";
		for(int i=0;i<c.getSize();i++)
			cuentas=cuentas+"\n"+(c.getItem(i));
		JOptionPane.showMessageDialog(null,cuentas);
		}
	}
	
	static void hacerRetiro() throws Saldo, Retiro {
		int pos=0;
		float cantidad=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"No hay cuentas registradas en el sistema unu");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("De que cuenta desea retirar?\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    cantidad=Float.parseFloat(JOptionPane.showInputDialog("Cantidad"));
		    c.Retiro(cantidad);
		    if(!(c.getItem(pos-1).getSaldo()<cantidad)) {
		    c.getItem(pos-1).setSaldoActualizado(c.getItem(pos-1).getSaldo()-cantidad);
		    c.getItem(pos-1).setFechaActualizacion(LocalDate.now());
		    JOptionPane.showMessageDialog(null,"No le vayan a robar joven");
		    bandera=false;
		    }
		    else {
		    	JOptionPane.showMessageDialog(null,"No cuenta con tal cantidad");
		    }
		    }
		    else
		    	JOptionPane.showMessageDialog(null,"¿Acaso quiere robar?");
			}
		}
	}
	
	static void borrarCuenta() throws BorrarCuenta {
		int pos=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas");
		else {
			boolean bandera=true;
			while(bandera) {
		    String cuentas="";
		    for(int i=0;i<c.getSize();i++)
			    cuentas=cuentas+"\n"+(i+1)+")"+(c.getItem(i));
		    pos=Integer.parseInt(JOptionPane.showInputDialog("Seleccione que cuenta desea dar de baja\n"+cuentas));
		    if((c.getSize())>=pos&&pos>0) {
		    	c.BorrarCuenta(c.getItem(pos-1));
		    	c.clear(c.getItem(pos-1));
		    	JOptionPane.showMessageDialog(null,"Cuenta dada de baja");
		    	bandera=false;
		    }	
		    else
		    	JOptionPane.showMessageDialog(null,"No hay cuentas, no puede dar de baja");
		  }
		}
	}
	
	static void consulta() {
		int respuesta=0;
		boolean ciclo=true;
		while(ciclo) {
		String opciones="Por favor indique su operacion:\n 1)Verificar el dinero de las cuentas generales\n 2)Promedio del dinero \n"
				+ " 3)Cuentas que cuentan con 10 mil pesos o más\n"
				+ " 4)Cuentas con saldo lleno.\n 5)Cuentas con el saldo mínimo\n 6)Salir";
		respuesta=Integer.parseInt(JOptionPane.showInputDialog(opciones));
		
		switch(respuesta) {
		case 1:montoTotal();ciclo=false;break;
		
		case 2:montoPromedio();ciclo=false;break;
		
		
		case 3:mayor10mil();ciclo=false;break;
		
		case 4:saldoMax();ciclo=false;break;
		
		case 5:saldoMin();ciclo=false;break;
		case 6:ciclo=false;break;
		
		default:JOptionPane.showMessageDialog(null,"Ingrese aqui la opción que quiera");
		  }
		}
	}
	
	static void montoTotal() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas en el sistema");
		else {
		    float montoTotal=0;
		    
		    for(int i=0;i<c.getSize();i++) 
			    montoTotal=montoTotal+c.getItem(i).getSaldo();
		    JOptionPane.showMessageDialog(null,"Su monto es: $"+montoTotal);
		}
		
	}
	
	static void montoPromedio() {
		float montoProm=0;
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas en el sistema");
		else {
		    float montoTotal=0;
		    for(int i=0;i<c.getSize();i++) 
		    	
		        montoTotal=montoTotal+c.getItem(i).getSaldo();
		    montoProm=montoTotal/c.getSize(); 
		    JOptionPane.showMessageDialog(null,"Su monto es: $"+montoProm);
		    
		}
	}
	
	
	static void mayor10mil() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas en el sistema");
		else {
			int vacio=0;
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			for(int i=0;i<c.getSize();i++)
				
				if(c.getItem(i).getSaldo()>10000) 
					copia[i-vacio]=c.getItem(i);
			
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				
				cuentas=cuentas+"\n"+copia[j];
			
			JOptionPane.showMessageDialog(null,"Las cuentas que tienen más de $10,000 son:\n"+cuentas);
		}
	}
	
	static void saldoMax() {
		if(c.isFree())
			JOptionPane.showMessageDialog(null,"Sin cuentas en el sistema");
		else {
			int vacio=0;
			
			float max=c.getItem(0).getSaldo();
			for(int i=0;i<c.getSize();i++)
				
				if(c.getItem(i).getSaldo()>max)
					max=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			
			for(int i=0;i<c.getSize();i++)
				if(c.getItem(i).getSaldo()==max) 
					
					copia[i-vacio]=c.getItem(i);
			
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				
				cuentas=cuentas+"\n"+copia[j];
			
			
			JOptionPane.showMessageDialog(null,"Esta(s) será(n) la(s) cuenta(s) con saldo más elevado:\n"+cuentas);
		}
		
	}
	
	static void saldoMin() {
		
		if(c.isFree())
			
			JOptionPane.showMessageDialog(null,"Sin cuentas en el sistema");
		else {
			int vacio=0;
			
			float min=c.getItem(0).getSaldo();
			
			for(int i=0;i<c.getSize();i++)
				
				if(c.getItem(i).getSaldo()<min)
					min=c.getItem(i).getSaldo();
			CuentaBancaria copia[]=new CuentaBancaria[c.getSize()];
			
			for(int i=0;i<c.getSize();i++)
				
				if(c.getItem(i).getSaldo()==min) 
					
					copia[i-vacio]=c.getItem(i);
				else
					vacio++;
			String cuentas="";
			for(int j=0;j<(c.getSize()-vacio);j++)
				cuentas=cuentas+"\n"+copia[j];
			JOptionPane.showMessageDialog(null,"Esta es la cuenta con menor saldo:\n"+cuentas);
		}
	}

	static void grabarRegistro() throws FileNotFoundException {
		if (c.isFree()) {
			
		}
		else {
			archivo=new eArchivo("cuentas a guardar");
			for(int i=0;i<c.getSize();i++) {
				archivo.writeLong(c.getItem(i).getNumCuenta());
				archivo.writeString(c.getItem(i).getNomCliente());
				archivo.writeFloat(c.getItem(i).getSaldo());
				if(c.getItem(i).getFechaActualizacion()==null) {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn("null");	
				}
				else {
					archivo.writeString(c.getItem(i).getFechaApertura().toString());
					archivo.writeStringLn(c.getItem(i).getFechaActualizacion().toString());	
				}
				
			}
			archivo.close();
		}
					
	}
	
	static void IniciarP() throws FileNotFoundException, NumeCuenta, CuentaExistente {
		boolean hay=false;
		try {
			archivo2= new lArchvo("guardar");
			hay=true;
		}catch(FileNotFoundException e) {
			System.err.println("Cuenta existente,crear otra");
		}
		if(hay)
			LTexto();
		
	}
	
static void LTexto() throws NumeCuenta, CuentaExistente {
		
		while(!archivo2.isEOF()) {
			e=new CuentaBancaria();
			e.setNumeCuenta(archivo2.readLong());
			e.setNomCliente(archivo2.readString());
			e.setSaldoActualizado(archivo2.readFloat());
			e.setFechaApertura(LocalDate.parse(archivo2.readString()));
			String fechaAct;
			fechaAct=archivo2.readString();
			if(fechaAct.equals("null")) 
				e.setFechaActualizacion(null);
			else
				e.setFechaActualizacion(LocalDate.parse(fechaAct));
			c.addItem(e);
			archivo2.isFinLinea();
		
			
		}
	}

}