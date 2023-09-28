package TDADiccionario;

import java.util.Iterator;

import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.*;

/**
 * Class DiccionarioHashAbierto. 
 * Implementa un Diccionario con Hash Abierto a partir de la interfaz Dictionary.
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile.
 * @param <K> 
 * @param <V>
 */

public class DiccionarioHashAbierto<K,V> implements Dictionary<K,V> {
	protected PositionList<Entrada<K,V>>[] arregloBucket;
	protected int n;	//cant entradas
	protected int N;	//tamaño arreglo
	protected static final float factor = 0.5f;
	
	//Constructor
	/**
	 * Crea un diccionario con un arreglo de 11 listas(buckets) vacías, con tamaño 0. 
	 */
	public DiccionarioHashAbierto() {
		N = 11;
		arregloBucket = (PositionList<Entrada<K,V>> []) new ListaDE[N];	//arreglo de listas
		for(int i = 0; i < N; i++)
			arregloBucket[i] = new ListaDE<Entrada<K,V>>();
		n = 0;
	}
	/**
	 * Aplica la función de compresión k MOD N a una clave k.
	 * @param k Clave a la que se le aplica la función.
	 * @return La clave hash de la clave ingresada por parámetro. 
	 * @throws InvalidKeyException Si la clave ingresada por parámetro no es válida. 
	 */
	private int HashThisKey(K k) throws InvalidKeyException{
		checkKey(k);
		return Math.abs(k.hashCode() % N);
	}
	/**
	 * Chequea que la clave ingresada por parámetro sea válida. 
	 * @param clave Clave a chequear. 
	 * @return La clave ingresada ya validada. 
	 * @throws InvalidKeyException Si la clave es nula.
	 */
	private K checkKey(K clave) throws InvalidKeyException {
		if(clave == null)
			throw new InvalidKeyException("Clave inválida. La misma es nula. ");
		return clave;
	}
	/**
	 * Examina el siguiente número primo al número ingresado por parámetro. 
	 * @param num Número entero al que se le encuentra su número primo siguiente. 
	 * @return El número primo siguiente al entero ingresado por parámetro. 
	 */
	private int nextPrimo(int num) {
		num++;
		for(int i = 2; i < num;i++)		//sale del bucle sin num no es divisible por ningun numero [2,num-1]
			if(num % i == 0) {
				num++;
				i = 2;
			}
		return num;
	}
	/**
	 * Crea un arreglo con el tamaño del siguiente número primo de N e inserta todas las entradas del viejo arreglo
	 * en el nuevo. 
	 * 
	 */
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		N = nextPrimo(N);
		n = 0;
		arregloBucket = new PositionList[N];
		for(int i = 0; i < N; i++)
			arregloBucket[i] = new ListaDE<Entrada<K,V>>();
		for(Entry<K,V> e: entradas)
			try {
			this.insert(e.getKey(), e.getValue());
			} catch(InvalidKeyException ex) {
				System.out.println(ex.getMessage());
			}
	}
	public int size() {
		return n;
	}
	public boolean isEmpty() {
		return n == 0;
	}
	public Entry<K,V> find(K key) throws InvalidKeyException {
		checkKey(key);
		Entry<K,V> retorno = null;
		boolean encontre = false;
		Iterator<Entrada<K,V>> it = arregloBucket[HashThisKey(key)].iterator();
		while(it.hasNext() && !encontre) {
			Entrada<K,V> ent = it.next();
			if(ent.getKey().equals(key)) {
				encontre = true;
				retorno = ent;
			}
		}
		return retorno; 
	}
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException {
		checkKey(key);
		PositionList<Entry<K,V>> entradas = new ListaDE<Entry<K,V>>();
		for(Position<Entrada<K,V>> p: arregloBucket[HashThisKey(key)].positions())
			if(p.element().getKey().equals(key))
				entradas.addLast(p.element());
		return entradas;
	}
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		K clave = checkKey(key);
		Entrada<K,V> nuevo = new Entrada<K,V>(clave,value);
		arregloBucket[HashThisKey(clave)].addLast(nuevo);
		n++;
		if(n/N > factor)
			reHash();
		return nuevo;
	}
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException {
		int claveHash;
		if(e == null)
			throw new InvalidEntryException("Entrada nula. ");
		try {
		claveHash = HashThisKey(e.getKey());
		} catch(InvalidKeyException ex) {
			throw new InvalidEntryException("Clave inválida. ");
		}
		Entrada<K,V> retorno = null;
		Iterator<Position<Entrada<K,V>>> it = arregloBucket[claveHash].positions().iterator();
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			Position<Entrada<K,V>> p = it.next();
			retorno = p.element();
			if(retorno == e) {
				encontre = true;
				try {
					arregloBucket[claveHash].remove(p);
					n--;
				} catch (InvalidPositionException e1) {
					e1.getMessage();;
				}
			}
		}
		if(!encontre) throw new InvalidEntryException("La entrada no pertenece al diccionario. ");
		return retorno;
	}
	public Iterable<Entry<K,V>> entries() {
		PositionList<Entry<K,V>> entradas = new ListaDE<Entry<K,V>>();
		for(int i = 0; i < N; i++)
			for(Position<Entrada<K,V>> p: arregloBucket[i].positions())
				entradas.addLast(p.element());
	return entradas;
	}
}
