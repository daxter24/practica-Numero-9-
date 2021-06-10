//equipo 1
//equipo 1

package Clases.ito.poo;

import java.io.Serializable;
import java.time.LocalDate;

import excepcion.ito.poo.NumeCuenta;
import excepcion.ito.poo.Saldo;

@SuppressWarnings("Serializable")
public class miCuentaBancaria implements Comparable<CuentaBancaria>, Serializable {
	
	static CuentasBanco c;
	
	private long numCuenta = 0L;
	private String nomCliente = "";
	private float Saldo = 0F;
	private LocalDate fechaApertura = null;
	private LocalDate fechaActualizacion = null;
	
	public CuentaBancaria() {
		
		super();
		
	}
	
	private void NumeDeCuenta(long numCuenta) throws NumeCuenta{
		if (9999>=numCuenta)
			throw new NumeCuenta("No se puede crear una cuenta menor a 9999");
	}
	
	private void Saldo(float saldo) throws Saldo{
		if (saldo<5000F)
			throw new Saldo("Por estándares de la empresa, debes tener por lo menos 5000$ pesos o más para crear una cuenta");
	}
	
	
	
	
	public CuentaBancaria(long numCuenta, String nomCliente, float saldo, LocalDate fechaApertura)
			throws NumeCuenta,Saldo {
		super();
		NumeDeCuenta(numCuenta);
		this.numCuenta = numCuenta;
		this.nomCliente = nomCliente;
		Saldo(saldo);
		Saldo = saldo;
		this.fechaApertura = fechaApertura;
	}
	
	public boolean Deposito(float Cantidad)throws Saldo {
		boolean Deposito = false;
		if(this.fechaApertura==null)
			System.out.println("La cuenta no se encuentra disponible");
		else {
			Deposito = true;
			this.setSaldo(this.getSaldo()+Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		
		return Deposito;
	}
	

	public boolean Retiro(float Cantidad)throws Saldo {
		
		boolean Retiro = false;
		if(Cantidad<=this.getSaldo()) {
			Retiro = true;
			this.setSaldo(this.getSaldo()-Cantidad);
			this.fechaActualizacion=LocalDate.now();
		}
		else
			System.out.println("No cuenta con saldo suficiente");
		return Retiro;
	}

	
	public long getNumCuenta() {
		
		return this.numCuenta;
	}

	public void setNumeCuenta(long newNumCuenta)throws NumeCuenta {
		NumeDeCuenta(newNumCuenta);
		this.numCuenta = newNumCuenta;
	}

	
	public String getNomCliente() {
		return this.nomCliente;
	}

	
	public void setNomCliente(String newNomCliente) {
		this.nomCliente = newNomCliente;
	}

	public float getSaldo() {
		return this.Saldo;
	}

	public void setSaldo(float newSaldo)throws Saldo {
		Saldo(newSaldo);
		this.Saldo = newSaldo;
	}
	
	public void setSaldoActualizado(float newSaldo) {
		this.Saldo = newSaldo;
	}

	public LocalDate getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(LocalDate newFechaApertura) {
		this.fechaApertura = newFechaApertura;
	}

	public LocalDate getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDate newFechaActualizacion) {
		this.fechaActualizacion = newFechaActualizacion;
	}

	@Override
	public String toString() {
		return "CuentaBancaria [numCuenta=" + numCuenta + ", nomCliente=" + nomCliente + ", Saldo=" + Saldo
				+ ", fechaApertura=" + fechaApertura + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
	@Override
	public int compareTo(CuentaBancaria o) {
		int compare=0;
		if (this.numCuenta<o.getNumCuenta())
			compare=-1;
		else if(this.numCuenta>o.getNumCuenta())
			compare=1;
		return compare;
		
	}

}