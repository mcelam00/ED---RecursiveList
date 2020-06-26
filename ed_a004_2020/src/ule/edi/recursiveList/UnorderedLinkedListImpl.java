package ule.edi.recursiveList;

import java.util.NoSuchElementException;


public class UnorderedLinkedListImpl<T> extends AbstractLinkedListImpl<T> implements UnorderedListADT<T> {

	public UnorderedLinkedListImpl() {
		//	VacÃ­a
	}
	
	public UnorderedLinkedListImpl(T ... v) {
		//	AÃ±adir en el mismo orden que en 'v'
		for (T Vi : v) {
			addLast(Vi);
		}
	}
	
	@Override
	public void addFirst(T element) {
     
	Node<T> nuevo = new Node<T>(element);		
	
     if(element == null) {
    	 throw new NullPointerException();
     }
     else if(isEmpty() == true) {
    	 this.front = nuevo;
     }
     else {
    	 nuevo.next = front;
    	 this.front = nuevo;
     }
	
	}
	
	
	@Override
	public void addLast(T element) {
	
		Node<T> nuevo = new Node<T>(element);
		
		if(element == null) {
			throw new NullPointerException();
		}
		else if(isEmpty() == true){
			this.front = nuevo;
		}
		else {
			
			addLastRec(this.front, nuevo);
		}
		
	}
		
		
	private void addLastRec(Node<T> actual, Node<T> nuevo) {
		
		if(actual.next == null) {
			//estoy en el ultimo nodo y voy a insertar
			actual.next = nuevo;
			
		}
		else {
			addLastRec(actual.next, nuevo);
		}
	
	}

	
	@Override
	public void addBefore(T element, T target) {
		
		Node<T> nuevo = new Node<T>(element);
		
		if(element == null || target == null) {
			throw new NullPointerException();
			
		}
		else if(contains(target) == true){
			//si está en la lista miro si es el primero porque no se añade igual
			if(this.front.elem.equals(target)) {
				nuevo.next = this.front;
				this.front = nuevo;
			}
			else {
				addBeforeRec(this.front, target, nuevo);
								
			}
			
		}
		else {
			throw new NoSuchElementException(); 
		}
			
	}
	
	
	private void addBeforeRec(Node<T> actual, T target, Node<T> nuevo) {
		if(target.equals(actual.next.elem)) {
			nuevo.next = actual.next;
			actual.next = nuevo;
		}
		else {
			addBeforeRec(actual.next, target, nuevo);
		}

	}

		
}
