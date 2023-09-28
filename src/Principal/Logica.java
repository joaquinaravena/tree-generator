package Principal;

import java.util.Iterator;



import Exceptions.BoundaryViolationException;
import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.*;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDADiccionario.*;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

/**
 * Class Logica. 
 * Esta clase modela la lógica de un que programa perrmite calcular distintas métricas sobre un árbol 
 * general de nodos conformados por un par (char,int). Se asume que no hay rótulos repetidos. 
 * @author Alumnos Joaquín Aravena y Stéfano Graziabile. 
 */
public class Logica {
	private Tree<Par<Character, Integer>> arbol;
	
	/**
	 * Crea un Árbol General vacío de pares (Character,Integer).
	 * El número del par representa el grado (cantidad de hijos) del rótulo. 
	 */
	public Logica() {
		arbol = new ArbolGeneral<Par<Character, Integer>>();
	}
	
	/**
	 * Inserta un rótulo raíz al árbol con el carácter ingresado por parámetro y grado 0. 
	 * @param ch Carácter que representa al rótulo.  
	 */
	public void crearArbol(char ch) {
		Par<Character, Integer> p = new Par<Character,Integer>(ch, 0);
		try {
			arbol.createRoot(p);
		} catch (InvalidOperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Agrega un nuevo nodo con rótulo n (ingresado por parámetro), hijo del rótulo p también ingresado 
	 * por parámetro. 
	 * @param p Caracter que representa al rótulo del padre del rótulo del nodo a agregar. 
	 * @param n Caracter que representa al rótulo del nodo a agregar. 
	 */
	public void agregarNodo(char p, char n) {
		if(arbol != null) {
			Par<Character, Integer> padre;
			Par<Character, Integer> nuevo = new Par<Character, Integer>(n, 0);
			boolean encontre = false;
			Iterator<Position<Par<Character, Integer>>> it = arbol.positions().iterator();
			while(it.hasNext() && !encontre) {
				Position<Par<Character, Integer>> pos = it.next();
				if(pos.element().getRotulo() == p) {
					padre = pos.element();
					encontre = true;
					try {
						arbol.addLastChild(pos, nuevo);
					} catch (InvalidPositionException e) {
						System.out.println(e.getMessage());
					}
					padre.setGrado(padre.getGrado()+1);
				}
			}
		}
	}
	
	/**
	 * Busca y elimina del árbol al nodo del rótulo representado por el caracter ingresado por parámetro.
	 * Modifica el grado del padre.  
	 * @param ch Caracter que representa al rótulo del nodo a eliminar. 
	 */
	public void eliminarNodo(char ch) {
		if(arbol != null) {
			boolean encontre = false;
			Iterator<Position<Par<Character, Integer>>> it = arbol.positions().iterator();
			while(it.hasNext() && !encontre) {
				Position<Par<Character, Integer>> n = it.next();
				try {
					if(n.element().getRotulo() == ch) {
						encontre = true;
						if(n != arbol.root()) {
							Position<Par<Character,Integer>> padre = arbol.parent(n);
							int tamañoH = n.element().getGrado();
							padre.element().setGrado(padre.element().getGrado()-1+tamañoH);
						}
						arbol.removeNode(n);
					}
				}catch(InvalidPositionException | EmptyTreeException | BoundaryViolationException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Examina los grados junto al rótulo de cada nodo del árbol. 
	 * @return Un String con los pares(rótulo, grado) de todos los nodos del árbol. 
	 */
	public String obtenerGrados(){
		Dictionary<Character,Integer> d = new DiccionarioHashAbierto<Character,Integer>();
		String s = "";
		if(arbol != null) {
			Iterator<Par<Character,Integer>> it = arbol.iterator();
			Par<Character,Integer> pos = it.next();
			try {
				while(it.hasNext()) {
					d.insert(pos.getRotulo(), pos.getGrado());
					pos = it.next();
				}
				s = s+ "("+pos.getRotulo()+", "+pos.getGrado()+") "; 
			} catch (InvalidKeyException e) {
				System.out.println(e.getMessage());
			}
		}
		
		for(Entry<Character,Integer> ent: d.entries())
			s = s+"("+ent.getKey().toString()+", "+ent.getValue().toString()+") ";
		return s;
	}
	
	/**
	 * Examina el grado del árbol, que corresponde al grado mayor entre todos los nodos del mismo.  
	 * @return Un String con el valor del grado máximo entre todos los nodos del árbol. 
	 */
	public String obtenerGradoArbol() {
		String s = "";
		if(arbol != null) {
			int max = 0;
			for(Position<Par<Character,Integer>> pos : arbol.positions()) {
				if(pos.element().getGrado() > max)
					max = pos.element().getGrado();
			}
			s = String.valueOf(max);
		}
		return s;
	}
	
	/**
	 * Examina el camino desde la raíz del árbol hasta el nodo de un rótulo ingresado por parámetro.
	 * @param ch Rótulo del nodo al que se le examina el camino. 
	 * @return Un String con todos los nodos del camino en órden. 
	 */
	public String obtenerCamino(char ch) {
		String s = "";
		if(arbol != null) {
			Position<Par<Character,Integer>> pos = null, ult = null;
			Iterator<Position<Par<Character,Integer>>> it = arbol.positions().iterator();
			while(it.hasNext() && pos == null) {
				Position<Par<Character,Integer>> aux = it.next();
				if(aux.element().getRotulo() == ch) {
					pos = aux;
					ult = aux;
				}
			}
			if(arbol.size() > 1) {
				Stack<Position<Par<Character,Integer>>> pila = new PilaConEnlaces<Position<Par<Character,Integer>>>();
				Position<Par<Character,Integer>> padre = null;
				try {
					while(pos != arbol.root()) {
						padre = arbol.parent(pos);
						pos = padre;
						pila.push(padre);
					}
					while(!pila.isEmpty())
						s = s+"  "+ pila.pop().element().toString();
				} catch (EmptyTreeException | InvalidPositionException | BoundaryViolationException|EmptyStackException e) {
					System.out.println(e.getMessage());
				}
			}
			if(ult != null)
				s = s+"  "+ult.element().toString();
		}
		return s;
	}
	
	/**
	 * Muestra los rótulos de los nodos del árbol por niveles. 
	 * @return Un String con los rótulos del árbol por niveles. 
	 */
	public String mostrarRotuloPorNivel() {
		Queue<Position<Par<Character,Integer>>> cola = new ColaConArregloCircular<Position<Par<Character,Integer>>>();
		String s = "";
		Position<Par<Character, Integer>> v;
		try {
			cola.enqueue(arbol.root());
			cola.enqueue(null);
			while(!cola.isEmpty()) {
				v = cola.dequeue();
				if(v != null) {
					s = s+" "+v.element().toString();
					for(Position<Par<Character,Integer>> p : arbol.children(v)) {
						cola.enqueue(p);
					}
				}else {
					s = s+"\n";
					if(!cola.isEmpty())
						cola.enqueue(null);
				}
			}
		} catch (EmptyTreeException | EmptyQueueException|InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}

	/**
	 * Muestra los rótulos de los nodos del árbol en preorden. 
	 * @return Un String que concatena los rótulos de los nodos en preorden. 
	 */
	public String mostrarRotuloPreOrden() {
		String s = "";
		try {
			if(arbol.size() > 0) {
				PositionList<Par<Character,Integer>> l = new ListaDE<Par<Character,Integer>>();
				preO(arbol.root(),l);
				for(Position<Par<Character, Integer>> p:l.positions())
					s = s+"  "+p.element().toString();
				}
		}catch(EmptyTreeException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}
	/**
	 * Método auxiliar que recorre el árbol en Preorden.  
	 * @param r Posición del árbol donde inicia el recorrido. 
	 * @param l Lista donde se insertan los nodos del recorrido. 
	 */
	private void preO(Position<Par<Character, Integer>> r, PositionList<Par<Character, Integer>> l) {
		l.addLast(r.element());
		try {
			for(Position<Par<Character, Integer>> p: arbol.children(r))
				preO(p, l);
		}catch(InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Muestra los rótulos de los nodos del árbol en postorden.
	 * @return Un String que concatena los rótulos de los nodos en postorden. 
	 */
	public String mostrarRotuloPostOrden() {
		String s = "";
		try {
			if(arbol.size() > 0) {
				PositionList<Par<Character, Integer>> l = new ListaDE<Par<Character, Integer>>();
				postO(arbol.root(),l);
				for(Position<Par<Character, Integer>> p:l.positions())
					s = s+"  "+p.element().toString();
			}
		} catch (EmptyTreeException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}
	/**
	 * Método auxiliar que recorre el árbol en Postorden. 
	 * @param r Posición del árbol donde inicia el recorrido. 
	 * @param l Lista donde se insertan los nodos del recorrido. 
	 */
	private void postO(Position<Par<Character, Integer>> r, PositionList<Par<Character, Integer>> l) {
		try {
			for(Position<Par<Character, Integer>> p: arbol.children(r))
				postO(p,l);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		
		l.addLast(r.element());
	}

	/**
	 * Busca y elimina del árbol todos los nodos con grado k ingresado por parámetro.  
	 * @param k Grado de los nodos a eliminar. 
	 */
	public void eliminarNodosGradoK(int k) {
		String grado = obtenerGradoArbol();
		if(Integer.parseInt(grado) >= k) {
			PositionList<Position<Par<Character,Integer>>> l = new ListaDE<Position<Par<Character,Integer>>>();
				for(Position<Par<Character,Integer>> p: arbol.positions())
					if(p.element().getGrado() == k)
						l.addLast(p);
			remover(l);
		}
	}
	
	/**
	 * Método auxiliar que recorre y elimina las posiciones de una lista ingresada por parámetro, 
	 * modificando el grado del padre de cada nodo a eliminar. 
	 * @param l Lista con las posiciones a eliminar. 
	 */
	private void remover(PositionList<Position<Par<Character,Integer>>> l) {
		for(Position<Position<Par<Character,Integer>>> e:l.positions()) 
			try {
				if(e.element() != arbol.root()){
					Position<Par<Character,Integer>> padre = arbol.parent(e.element());
					int tamañoH = e.element().element().getGrado();
					padre.element().setGrado(padre.element().getGrado()-1+tamañoH );
			}
				arbol.removeNode(e.element());

			} catch (InvalidPositionException | BoundaryViolationException|EmptyTreeException e1) {
				System.out.println(e1.getMessage());
			}
	}

}