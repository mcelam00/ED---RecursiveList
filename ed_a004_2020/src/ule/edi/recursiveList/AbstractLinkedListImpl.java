package ule.edi.recursiveList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableException;

public class AbstractLinkedListImpl<T> implements ListADT<T> {

	// Estructura de datos, lista simplemente enlazada
	//
	// Este es el primer nodo de la lista
	protected Node<T> front = null;

	// Clase para cada nodo en la lista
	protected  class Node<T> {

		T elem;

		Node<T> next;

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		@Override
		public String toString() {
			return "(" + elem + ")";
		}

	}
	
	private class IteratorImpl implements Iterator<T> {
		private Node<T> actual;
		
		public IteratorImpl(Node<T> referencia) {
			actual = referencia;
		}
		
		
		@Override
		public boolean hasNext() {

		boolean testigo = true;
			
			if(actual == null) {
				testigo = false;
			}

		return testigo;
		}

		@Override
		public T next() {

			if(hasNext() == false) {
				throw new NoSuchElementException();
			}
			
			T elem = actual.elem;
			
			actual = actual.next;
			
			
			return elem;			
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


	// Ejemplos de ejercicios de recursividad
	//



	@Override
	public String toString() {
		//	Construye y devuelve con el formato adecuado "(A B C )" 
		
		String cadena = "()";
	
		if(isEmpty() == true) {
			return cadena;
		}
		else {
			
			cadena = toStringRec(this.front); //A B C )
			return "(" + cadena;
			
		}

	
	}
	
	
	
	private String toStringRec(Node<T> actual) {
		
		if(actual.next == null) {
			return actual.elem.toString() + " )";
		}
		else {
			
			return actual.elem.toString() + " " + toStringRec(actual.next);
			
		}
			
	}
	
	
	@Override
	public boolean contains(T target) {
		boolean testigo = false;
		
		if(target == null) {
			throw new NullPointerException();
		}		
		
		if(isEmpty() == true) {
			
			testigo = false;
		}
		else {
		
			testigo =  containsRec(this.front, target); 
		}
		
		return testigo;
	}

	private boolean containsRec(Node<T> actual, T elem) {
		
			if(elem.equals(actual.elem)) { //si el elemento por el que voy coincide ser igual al que se pasa
				//paro directamente y recojo las llamadas recursivas volviendo con true
				return true;				
			}
			else if(actual.next == null) { //otro caso es si no lo encuentra y se llega al final de la lista, en cuyo caso se propaga hacia atras en las llamadas recursivas el false
					//pongo aux.next para adelantarme al caso basico anterior
				return false;
				
			}
			else {
				//caso recursivo
				 return containsRec(actual.next, elem); //iria trayendo el false o el true hacia atras
				
			}		
	}
	
	
  @Override
	public int count(T element) {
	
	  if(element == null) {
		  throw new NullPointerException();
	  }
	  
	  int numElementos;
	  
	  if(isEmpty() == true) {
		  
		  numElementos = 0;
	  }
	  else {
		  
		  numElementos = countRec(this.front, element);
		 
	  }
	  
	  return numElementos;
		
	}


	private int countRec(Node<T> actual, T element) {
		int numApariciones;
		
			if(actual == null) { //cuando llego al final de la lista inicializo la variable en la que voy a acumular hacia atras segun los elementos que he marcado (segun las llamadas recursivas que haya abierto como que el elemento coincidia)
				numApariciones = 0;
				return numApariciones;
					
			}
			else {

				if(element.equals(actual.elem)) {
					numApariciones = 1 + countRec(actual.next, element); //si coincide, lo marco para sumarlo a la vuelta					
				}
				else { //es importante este else para no meternos en la otra recursividad siempre
					numApariciones = countRec(actual.next, element); //si no coincide sigo mirando en la lista
					
				}
				
				return numApariciones; //las llamadas recursivas no comparten memoria entonces lo que quiera pasar de una a otra lo hago mediante el return.
			}
		
	}
	


	@Override
	public T getLast() throws EmptyCollectionException {
		T elem = null;
		
		if(isEmpty() == true) {
			throw new EmptyCollectionException("AbstractLinkedList");
		}
		
		elem = getLastRec(this.front);
		
		return elem;
				
	}
	
	private T getLastRec(Node<T> actual) {
		T elem = null;
		
			if(actual.next == null) {
				elem = actual.elem;  //cuando el siguiente sea null estaré situado en el ultimo y lo retorno a la llamada anterior y de esa a la anterior y asi completando hasta la primera y a getLast
				return elem;
			}
			else {
				
				elem = getLastRec(actual.next); //esta linea es la responsable de que vayamos iterando
				return elem;
				
			}
		
		
	}

	
	@Override
	public boolean isOrdered() { //hacer una conversion del elemento que esta en el nodo a comparable como en el ejericio del moodle. En tiempo de ejecución si lo que sustituimos a T es un tipo que no implementa el interfaz comparable fallará
	//si el cast falla se dispara al excepcion java.lang.ClassCastException.
		//debe capturar esa excepcion mediante un try/catch y disparar en su lugar una excepcion de la colección
			//(en el catch capturamos esa y lanzamos la otra TypeIsNotComparableException que hereda de Runtime y asi no cambiamos la signatura)
		
		if (isEmpty() == true) { //si la lista es vacía
			return true;
		}
		else { //si tiene por lo menos un elemento
			return isOrderedRec(this.front);
		}
			
	}

	
	
	
	private boolean isOrderedRec(Node<T> actual) {
		Node<T> aux = actual;
				
		//voy a recorrer la lista hasta que haya uno que este desordenado o hasta que se alcance el final, en cuyo caso estarán todos ordendados
		try {
			if(aux.next == null) { //porque siempre comparo con el siguiente, entonces debo adelantarme para no obtener exception de puntero nulo
				return true;
			}
			else if( ((Comparable<T>)aux.elem).compareTo(aux.next.elem) >0) { //si el actual es mayor que el siguiente
				return false;			
			}
			else {
				return isOrderedRec(aux.next);			
				
			}
			
		}catch (ClassCastException e) {
				throw new TypeIsNotComparableException();
		}
	}
	
	
	
	


	@Override
	public T remove(T element) throws EmptyCollectionException {
   
		if(element == null) {
			throw new NullPointerException();
		}
		else if(isEmpty() == true){
			throw new EmptyCollectionException("AbstractLinkedList");
		}
		else if(contains(element) == false) {
			throw new NoSuchElementException();
			
		} //PRECONDICIÓN: Lo va a contener si o si
		T elem =  null;
		
		if(element.equals(this.front.elem)) { //si el primer elemento es el que es igual
				
				elem = removeFirstElem();			
			
		}
		else { //caso general en el que es el segundo o superiores
				
				elem = removeRec(this.front, element); 
			
		}
		
		return elem;		
	}
	

	private T removeRec(Node<T> actual, T elemento) {
		T elem;
		
		if(elemento.equals(actual.next.elem)) { //me paro uno antes para poder pasar la flecha al siguiete al que es igual
			elem = actual.next.elem; //salvo el elemento
			actual.next = actual.next.next; //cambio la flecha
			return elem;
			
		}
		else {
			elem = removeRec(actual.next, elemento);
			return elem;
		}
		
	}

	private T removeFirstElem() {
		T elem = null;
		
		elem = this.front.elem;
		this.front = this.front.next;
		
		return elem;
	}

	
	@Override
	public T removeLast(T element) throws EmptyCollectionException {
		
		if(element == null) {
			throw new NullPointerException();
		}
		else if(isEmpty() == true) {
			throw new EmptyCollectionException("AbstractLinkedList");
		}
		else if(contains(element) == false) {
			throw new NoSuchElementException();
		}//No es posible que pase una lista que no contenga el elemento
		T elem = null;
	
			elem = removeLastRec(this.front, element);
				
		return elem;	
	}

	private T removeLastRec(Node<T> actual, T element) {
		T elem = null;
		
			if(actual != null) {
				
				//hago las llamadas recursivas
				elem = removeLastRec(actual.next, element);
				
				//cuando vuelvo, voy mirando: Primero el ultimo desde la penultima posicion
				
				
				if(actual == front && elem == null) { //cuando sean la misma flecha estaré en la primera posicion, que sería por si la aparicion ultima aparicion es la primera porque solo hay una
					elem = front.elem;
					front = front.next;
					
				
				}else if(actual.next != null && elem == null) { //Para evitar que nada mas volver el actual penultimo se salga con puntero nulo o que si la lista sea de un unico elemento tambien se salga
				
				//caso general y caso del ultimo de la lista
					
					if(element.equals(actual.next.elem)) {
						elem = actual.next.elem;
						actual.next = actual.next.next;
					}

				
				}//elem == null para que no me elimine todas las instancias segun vuelve y solamente 1
			}
			
			return elem;		
	}


	@Override
	public boolean isEmpty() {
		
		return (this.front == null);	//si el front es null es que la lista es vacía
		
	}

	@Override
	public int size() {
		
		int tamanio = 0;
		
		tamanio = this.sizeRec(this.front);
		
		return tamanio;
	}
	
	
	private int sizeRec(Node<T> actual) {
		int cuenta;
		
			//caso base: cuando la lista esté vacía
			if(actual == null) {
				cuenta = 0; //inicializo la variable porque si lo hago fuera me lo pierde
				return cuenta;
				
			}
			else {
				
				cuenta = 1 + sizeRec(actual.next); //dejo un elemento contado para la vuelta que es cuando voy haciendo la cuenta
				return cuenta; //lo ejecuto cuando voy completando las llamadas recursivas (a la vuelta de la recursividad, una vez he llegado al caso base)
				
				
			}
		
	}
	
	

	@Override
	public T getFirst() throws EmptyCollectionException {
		
		if(isEmpty() == true) {
			throw new EmptyCollectionException("AbstractLinkedList");
		}
		else {
			
			return this.front.elem;
				
		}	
		
	}

	@Override
	public String toStringFromUntil(int from, int until) {

		String cadena = "";
	
		
		if(from <= 0 || until <= 0 || until < from) {
			throw new IllegalArgumentException();
		}
		else if(front == null || from > size()) { //si la lista es vacia o el from es mayor al size
						
			cadena = "()";
		}
		else {
			//Los parámetros formales son correctos
		int contador = 1;
		
		
			cadena = "(" + toStringFromUntilRec(front, from, until, contador, contador) + " )"; //paso el mismo porque luego el relevo de contar una vez se llega al front lo asume conU
			
			
		}
	return cadena;
		
	}

	private String toStringFromUntilRec(Node<T> actual, int from, int until, int conF, int conU) {
		
		
			//caso base
		if(conU == until || actual.next == null) { //si se llega al until o until > size
			//fin del despliegue de llamadas
			return actual.elem.toString();
			
		}
		else if(conF == from){
			//comienzo a adjuntar los elementos
			return actual.elem.toString() + " " + toStringFromUntilRec(actual.next, from, until, conF, ++conU); //llamo ahora incrementando el segundo contador hasta que salte el caso de arriba
			
		}
		else {
			//caso recursivo 2, avanzo hasta llegar al from
			return toStringFromUntilRec(actual.next, from, until, ++conF, conU);
			
		}
		
		
	}
	
	
	
	
	@Override
	public String toStringReverse() {

		return toStringReverseRec(this.front) + ")"; //le adjunto el ultimo paréntesis
				
	}
	
	
	
	

	private String toStringReverseRec(Node<T> actual) {
		Node<T> aux = actual;
		String cadena = "";
		
			if(aux == null) {
				cadena = "("; //al llegar al final comienzo a anexar a la cadena
				return cadena;
			}
			else {
				
				cadena = toStringReverseRec(aux.next) + aux.elem.toString() + " " ; //a la cadena que me va volviendo le voy anexando el elemento actual y el espacio
				return cadena;
				
			}
		
	}


	@Override
	public int removeDuplicates() {
		
		int contador = 0;
		int eliminados = 0;
		
		eliminados = recorreRec(this.front, contador);
		
		
		return eliminados;
		
	}
	
	private int recorreRec(Node<T> actual, int contador) {
		
		
		if(actual == null) { //mientras que siga habiendo elementos en la lista no vamos a parar
			//en el momento en que llegue al final de la lista no quiero perder el numero de elminados que he venido pasando del metodo backtracking a este, y acumulando hasta aquí, entonces lo devulevo

			return contador; //en definitiva subimos el acumulado todo hasta el metodo público
		}
		else {
			contador = contador + removeDuplicatesRec(actual, actual.elem); //en cada llamada recursiva a este metodo añadiré al contador el numero de elementos que llevo eliminados
			
		
			return recorreRec(actual.next, contador);  //le paso el contador de cada vez que vuelvo hacia atrás para pasar al siguiente y no perder el numero de eliminados de ese elemento
			
		}
		
	}
	
	private int removeDuplicatesRec(Node<T> actual, T elem) { //elem permanece invariable durante todo el reocrrido porque es el target
		
		
		if(actual.next == null) { //si la lista es vacía salta primero el anterior que este
			return 0;
	
		}
		else {
			if(actual.next.elem.equals(elem)) {
				//lo elimino
				actual.next = actual.next.next;
				

				//ojo, porque si encuentro el duplicado no me desplazo, porque el siguiente viene a mi al quitar el duolicado
				return 1 + removeDuplicatesRec(actual, elem);
				
				
			}
			else { //El siguiente ya no es igual, entonces me desplazo uno
				//seguiré recorriendo igualmente
				return removeDuplicatesRec(actual.next, elem);
			}
			
		}	
		
		
	}
	
	

	
	@Override
	public Iterator<T> iterator() {
		return new IteratorImpl(this.front);
	}


}
