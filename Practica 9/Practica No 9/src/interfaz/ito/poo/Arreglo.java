package interfaz.ito.poo;

import excepcion.ito.poo.*;

public interface Arreglo<T> {
	
	public boolean addItem(T item) throws CuentaExistente;
	
	public boolean hayItem(T item);
	
	public T getItem(int pos);
	public int getSize();
	
	public boolean clear(T item);
	public boolean isFree();
	
	public boolean isFull();
}