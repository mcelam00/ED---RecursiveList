package ule.edi.recursiveList;

import ule.edi.exceptions.EmptyCollectionException;

public class OrderedLinkedListImpl<T extends Comparable<? super T>> extends
		AbstractLinkedListImpl<T> implements OrderedListADT<T> {

	public OrderedLinkedListImpl() {
		// Vacía
	}

	public OrderedLinkedListImpl(T... v) {
		// Añade todos los elementos del array 'v'
		for (T Vi : v) {
			add(Vi);
		}
	}



	
	@Override
	public void add(T element) { //se ha indicado el extends comparable, es decir, debe ssutituirse T en timepo de jececucion por alguna clase que implemente el interfaz comparable o que lo tenga dentro de sus jerarqu�a
		//podemos utilizar el elemento directamente llamando a compareTo sin la necesidad de castearlo como si tenemos que hacer en la abstract en la Abstract
		
		Node<T> nuevo = new Node<T>(element);
		
		if(element == null) {
			throw new NullPointerException();
		}
		else if(isEmpty() == true){ //si es vacia
			this.front = nuevo;
		}
		else if(this.front.elem.compareTo(element) >=0) { //Se compara el primero con el elemento que se me pasa por si hay que insertarlo el primero
				//si el siguiente es mayor o igual lo inserto delante
				nuevo.next = this.front;
				this.front = nuevo;
			
		}
		else {
			addRec(this.front, nuevo);
		}
			
	}
	
	private void addRec(Node<T> actual, Node<T> insertar) {
		
		if(actual.next == null) { //si se llega al ultimo porque nos dan la Z por ejemplo que va la ultima
			actual.next = insertar;
			
		}//Aqui problema mirar bien a mano el constructor del test
		else if(actual.next.elem.compareTo(insertar.elem) >= 0) { //lo inserto detras de actual (o delante si es igual)
			insertar.next = actual.next;
			actual.next = insertar;			
		}
		else {
			addRec(actual.next, insertar);
		}
		
	}
	
	

	@Override
	public int removeDuplicates() {
		// Redefinir para listas ordenadas (los duplicados estarán consecutivos)
		int contador = 0;

		return recorreListaRec(this.front, contador);  //el caso de vacia o de 1 elemento ya estan pensados porque devuelve 0 (no hay posibilidad de empty collection)

	
		}

	private int recorreListaRec(Node<T> actual, int contador) {
		
		if(actual == null || actual.next == null) { //el ultimo nunca va a tener duplicados por detras, y si los tenia por delante ya los he quitado (por si acaso hubiera 2 iguales al final pongo actua == null tmb)
			return contador; //al final retorno el contador con todos los eliminados acumulados hacia arriba
		}
		else {
			contador = contador + removeDuplicatesRec(actual, actual.elem); //con el elemento actual
			
			//avanzo al siguiente elemento 
			return recorreListaRec(actual.next, contador);
		}
		
		
	}
	
	private int removeDuplicatesRec(Node<T> actual, T elem) {
		int contador = 0; //si directamente el siguiente no es duplicado no quita ninguno
		
		if(actual.next != null) { //para que no trate de mirar el elemento si el siguiente es null
			
			
			if(actual.next.elem.equals(elem)) { //mientras vayan quedando iguales se van borrando
				
				actual.next = actual.next.next;
				
				contador = 1 + removeDuplicatesRec(actual, elem); //llamo sin moverme porque comparo el siguiente (el siguiente ira viniendo a mi conforme vaya eliminando los iguales)
				
				
			}
		}
		return contador;
	}


	

		

}
