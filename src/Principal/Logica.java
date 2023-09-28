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
 * Esta clase modela la l�gica de un que programa perrmite calcular distintas m�tricas sobre un �rbol 
 * general de nodos conformados por un par (char,int). Se asume que no hay r�tulos repetidos. 
 * @author Alumnos Joaqu�n Aravena y St�fano Graziabile. 
 */
public class Logica {
	private Tree<Par<Character, Integer>> arbol;
	
	/**
	 * Crea un �rbol General vac�o de pares (Character,Integer).
	 * El n�mero del par representa el grado (cantidad de hijos) del r�tulo. 
	 */
	public Logica() {
		arbol = new ArbolGeneral<Par<Character, Integer>>();
	}
	
	/**
	 * Inserta un r�tulo ra�z al �rbol con el car�cter ingresado por par�metro y grado 0. 
	 * @param ch Car�cter que representa al r�tulo.  
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
	 * Agrega un nuevo nodo con r�tulo n (ingresado por par�metro), hijo del r�tulo p tambi�n ingresado 
	 * por par�metro. 
	 * @param p Caracter que representa al r�tulo del padre del r�tulo del nodo a agregar. 
	 * @param n Caracter que representa al r�tulo del nodo a agregar. 
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
	 * Busca y elimina del �rbol al nodo del r�tulo representado por el caracter ingresado por par�metro.
	 * Modifica el grado del padre.  
	 * @param ch Caracter que representa al r�tulo del nodo a eliminar. 
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
							int tama�oH = n.element().getGrado();
							padre.element().setGrado(padre.element().getGrado()-1+tama�oH);
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
	 * Examina los grados junto al r�tulo de cada nodo del �rbol. 
	 * @return Un String con los pares(r�tulo, grado) de todos los nodos del �rbol. 
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
	 * Examina el grado del �rbol, que corresponde al grado mayor entre todos los nodos del mismo.  
	 * @return Un String con el valor del grado m�ximo entre todos los nodos del �rbol. 
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
	 * Examina el camino desde la ra�z del �rbol hasta el nodo de un r�tulo ingresado por par�metro.
	 * @param ch R�tulo del nodo al que se le examina el camino. 
	 * @return Un String con todos los nodos del camino en �rden. 
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
	 * Muestra los r�tulos de los nodos del �rbol por niveles. 
	 * @return Un String con los r�tulos del �rbol por niveles. 
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
	 * Muestra los r�tulos de los nodos del �rbol en preorden. 
	 * @return Un String que concatena los r�tulos de los nodos en preorden. 
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
	 * M�todo auxiliar que recorre el �rbol en Preorden.  
	 * @param r Posici�n del �rbol donde inicia el recorrido. 
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
	 * Muestra los r�tulos de los nodos del �rbol en postorden.
	 * @return Un String que concatena los r�tulos de los nodos en postorden. 
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
	 * M�todo auxiliar que recorre el �rbol en Postorden. 
	 * @param r Posici�n del �rbol donde inicia el recorrido. 
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
	 * Busca y elimina del �rbol todos los nodos con grado k ingresado por par�metro.  
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
	 * M�todo auxiliar que recorre y elimina las posiciones de una lista ingresada por par�metro, 
	 * modificando el grado del padre de cada nodo a eliminar. 
	 * @param l Lista con las posiciones a eliminar. 
	 */
	private void remover(PositionList<Position<Par<Character,Integer>>> l) {
		for(Position<Position<Par<Character,Integer>>> e:l.positions()) 
			try {
				if(e.element() != arbol.root()){
					Position<Par<Character,Integer>> padre = arbol.parent(e.element());
					int tama�oH = e.element().element().getGrado();
					padre.element().setGrado(padre.element().getGrado()-1+tama�oH );
			}
				arbol.removeNode(e.element());

			} catch (InvalidPositionException | BoundaryViolationException|EmptyTreeException e1) {
				System.out.println(e1.getMessage());
			}
	}

}