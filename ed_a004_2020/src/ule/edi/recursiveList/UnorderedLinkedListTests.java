package ule.edi.recursiveList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.LayoutStyle;

import org.hamcrest.core.IsEqual;
import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.exceptions.TypeIsNotComparableException;
import ule.edi.model.Person;


public class UnorderedLinkedListTests {

	

	private UnorderedLinkedListImpl<String> lS;
	private UnorderedLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new UnorderedLinkedListImpl<String>();
		
		
		this.lSABC = new UnorderedLinkedListImpl<String>("A", "B", "C");
	}
	
   @Test
   public void constructorElemens(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }

// TESTS DE addFirst
   @Test
   public void addFirstTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addFirst("C");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addFirst("B");
	   Assert.assertEquals("(B C D )", lS.toString());
	   lS.addFirst("A");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   // TESTS DE addBefore
   
   @Test
   public void addBeforeTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addBefore("C", "D");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addBefore("A","C");
	   Assert.assertEquals("(A C D )", lS.toString());
	   lS.addBefore("B", "C");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   //Tests toStringReverse 
 
   @Test
   public void toStringReverse(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
	   Assert.assertEquals("(D C B A )", lS.toStringReverse());
		  
   }
   

   
// Tests eliminar duplicados
	
   @Test
	public void testRemoveDuplicates() {
	    UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		Assert.assertEquals(lista.removeDuplicates(),4); 
		Assert.assertEquals(lista.toString(), "(A B C )");
		Assert.assertEquals(lSABC.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lSABC.toString(), "(A B C )");	
	
	}
  
   
   
// AÃ‘ADIR MAS TESTS para el resto de casos especiales y para el resto de mÃ©todos
 // de las clases AbstractLinkedListImpl y UnorderedLinkedListImpl
   
   @Test
 	public void testToStringNode() {
	   lS.addFirst("ZZ");
	   
	   Assert.assertEquals("(ZZ)", lS.front.toString());
	   
   }
	
   @Test
  	public void testToString() {

	   Assert.assertEquals("(A B C )", lSABC.toString());
	   
	   
   }
   
   @Test
 	public void testToStringEmptyList() {

	   Assert.assertEquals("()", lS.toString());
	   
	   
  }

   @Test
  	public void testContains() {

	   Assert.assertTrue(lSABC.contains("A"));
	   lS.addFirst("B");
	   Assert.assertTrue(lSABC.contains("B"));
	   Assert.assertTrue(lSABC.contains("B"));
	   Assert.assertTrue(lSABC.contains("C"));
 	   
   }
 
   @Test
 	public void testContainsEmpty() {

	   Assert.assertFalse(lS.contains("A"));
	  
	   
  }
   @Test
	public void testContainsOneElem() {
	  
	   lS.addFirst("B");
	   Assert.assertTrue(lS.contains("B"));
	  
	   
 }
   
   @Test
	public void testContainsNotFound() {

	   Assert.assertFalse(lSABC.contains("W"));
	  
	   
 }
   
   @Test
 	public void testCount() {
	   Assert.assertEquals(1, lSABC.count("C"));
	   lSABC.addBefore("A", "C");
	   Assert.assertEquals(2, lSABC.count("A"));
   }
   
   @Test
	public void testCountEmptyList() {
	   Assert.assertEquals(0, lS.count("A"));

   }
   
   @Test
  	public void testCount1Elem() {
	   lS.addFirst("A");
	   Assert.assertEquals(1, lSABC.count("A"));
     }
   
   @Test
 	public void testGetLast() throws EmptyCollectionException{ //no hereda de runtimeException
	   Assert.assertEquals("C", lSABC.getLast());
	   
   }
  
    @Test
	public void testGetLast1Elem() throws EmptyCollectionException{
    	lS.addFirst("B");
 	   Assert.assertEquals("B", lS.getLast());
    }
	   
    @Test
	public void testisOrdered(){
    	Assert.assertTrue(lSABC.isOrdered());
    }
	   
    @Test
  	public void testisOrderedNotOrdered(){
    	lS.addFirst("Z");
    	lS.addLast("A");
    	Assert.assertFalse(lS.isOrdered());

    }  
  
    @Test
  	public void testisOrderedEmpty(){
    	Assert.assertTrue(lS.isOrdered());

    }  
   
    @Test
  	public void testisOrdered1Elem(){
       	lS.addLast("A");
    	Assert.assertTrue(lS.isOrdered());
    } 
    
    @Test
  	public void testremove() throws EmptyCollectionException{ 
    	
    	lSABC.addLast("B");
    	lSABC.addLast("D");
    	lSABC.addLast("A");
    	lSABC.addLast("B");
    	
    	Assert.assertEquals("A", lSABC.remove("A"));
    	Assert.assertEquals("(B C B D A B )", lSABC.toString());
    	
    	lSABC.addLast("D");
    	Assert.assertEquals("D", lSABC.remove("D"));
    	Assert.assertEquals("(B C B A B D )", lSABC.toString());

    }
    
    @Test
  	public void testremove1Elem() throws EmptyCollectionException{
    	
    	lS.addFirst("B");
    	Assert.assertEquals("B", lS.remove("B"));
    	Assert.assertTrue(lS.isEmpty());
    	
    	
    }
    
    @Test
  	public void testremoveFirstIfLast() throws EmptyCollectionException{
 	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
 	   
 	   Assert.assertEquals("D", lS.remove("D"));
 	   Assert.assertEquals("(A B C )", lS.toString());
    }
    
    @Test
  	public void testremoveLast() throws EmptyCollectionException{
    	lSABC.addLast("B");
    	lSABC.addLast("D");
    	lSABC.addLast("A");
    	lSABC.addLast("B");
    	
    	Assert.assertEquals("A", lSABC.removeLast("A"));
    	Assert.assertEquals("(A B C B D B )", lSABC.toString());
    	lSABC.addLast("B"); 
    	
    	Assert.assertEquals("B", lSABC.removeLast("B"));
    	Assert.assertEquals("(A B C B D B )", lSABC.toString());
    	
    }
    
    @Test
  	public void testremoveLastIfFirst() throws EmptyCollectionException{
    	
    	lSABC.addFirst("Z");
    	Assert.assertEquals("Z", lSABC.removeLast("Z"));
    	Assert.assertEquals("(A B C )", lSABC.toString());

    	
    }
    
    @Test
  	public void testremoveLast1Elem() throws EmptyCollectionException{
    	
    	lS.addFirst("Z");
    	Assert.assertEquals("Z", lS.removeLast("Z"));
    	Assert.assertTrue(lS.isEmpty());

    }
    
    @Test
  	public void testSize(){
    	Assert.assertEquals(3, lSABC.size());

    	
    }
    
    @Test
  	public void testSizeEmpty(){
    	Assert.assertEquals(0, lS.size());

    }
    
    @Test
  	public void testgetFirst() throws EmptyCollectionException{
    	Assert.assertEquals("A", lSABC.getFirst());

    }
    
    @Test
  	public void testgetFirst1Elem() throws EmptyCollectionException{
    	lS.addFirst("X");
    	Assert.assertEquals("X", lS.getFirst());

    }
    
    @Test
  	public void testtoStringFromUntil() {
    	
    	Assert.assertEquals("(A B C )", lSABC.toStringFromUntil(1, 3));
    	Assert.assertEquals("(B C )", lSABC.toStringFromUntil(2, 3));
    	Assert.assertEquals("(C )", lSABC.toStringFromUntil(3, 3));
    	Assert.assertEquals("(A B )", lSABC.toStringFromUntil(1, 2));


    }
    
    @Test
  	public void testtoStringFromUntilUntilGreater() {
    	Assert.assertEquals("(B C )", lSABC.toStringFromUntil(2, 20));
    }
    
  	 @Test
     public void testtoStringFromUntilBothGreater() {
     	Assert.assertEquals("()", lSABC.toStringFromUntil(10, 20));

      }
  	 
  	@Test
    public void testtoStringFromUntilEmpty() {
     	Assert.assertEquals("()", lS.toStringFromUntil(1,1));
     	Assert.assertEquals("()", lS.toStringFromUntil(10, 20));

     }
  	
  	@Test
    public void testtoStringFromUntil1Elem() {
  		lS.addFirst("Z");
     	Assert.assertEquals("(Z )", lS.toStringFromUntil(1, 1));
     	Assert.assertEquals("(Z )", lS.toStringFromUntil(1, 2));
     	Assert.assertEquals("()", lS.toStringFromUntil(2, 2));


     }
    @Test
    public void testtoStringReverseEmpty(){
 	   Assert.assertEquals(lS.toString(), lS.toStringReverse());

    }
   
    @Test
    public void testtoStringReverse1Elem(){
    	lS.addFirst("Z");
 	   Assert.assertEquals(lS.toString(), lS.toStringReverse());

    }
    
    @Test
    public void testremoveDuplicatesEmpty(){
  	   Assert.assertEquals(0, lS.removeDuplicates());

    }
    
    @Test
    public void testremoveDuplicates1Elem(){
    	lS.addFirst("Z");
   	   Assert.assertEquals(0, lS.removeDuplicates());

    }
    
    @Test
    public void testremoveDuplicatesAll(){
    	lS.addFirst("Z");
    	lS.addFirst("Z");
    	lS.addFirst("Z");
    	lS.addFirst("Z");
    	lS.addFirst("Z");
   	   	Assert.assertEquals(4, lS.removeDuplicates());
   	   	
    }
    
    @Test
    public void testremoveDuplicates2Last(){
    	lSABC.addLast("Z");
    	lSABC.addLast("Z");
   	   	Assert.assertEquals(1, lSABC.removeDuplicates());
    }
    
    
    //TEST DEL ITERADOR
    
    @Test
    public void testIterator() {

    lS.addFirst("A");

    lS.addLast("B");

    //pedimos el iterador a la colección

    Iterator<String> miIt = lS.iterator();

    // probamos los métodos hasNext() y next()

    Assert.assertTrue(miIt.hasNext());

    Assert.assertEquals(miIt.next().toString(), "A");

    Assert.assertTrue(miIt.hasNext());

    Assert.assertEquals(miIt.next().toString(), "B");

    Assert.assertFalse(miIt.hasNext());


    }
    
    //TEST DE EXCEPCIONES

	@Test(expected = NoSuchElementException.class)
	public void testnextNoSuchElementException() {
		
		Iterator<String> miIt = lS.iterator();
	    Assert.assertEquals(miIt.next().toString(), "A");

	}
    
	@Test(expected = UnsupportedOperationException.class)
	public void testremoveUnsupportedOperationException() {
		
		Iterator<String> miIt = lS.iterator();
		miIt.remove();
	}
	
	@Test(expected = NullPointerException.class)
	public void testcontainsNullPointerException() {
		
		lS.contains(null);
		
	}
    
	@Test(expected = NullPointerException.class)
	public void testcountNullPointerException() {
		
		lS.count(null);
		
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testgetLastEmptyCollectionException() throws EmptyCollectionException {
		
		lS.getLast();
		
	}
	
	@Test(expected = TypeIsNotComparableException.class)
	public void testisOrderedTypeIsNotComparableException() throws TypeIsNotComparableException {
		
	    UnorderedLinkedListImpl<Object[]> lista=new UnorderedLinkedListImpl<Object[]>(); 
	    lista.addFirst(new Object[2]);
	    lista.addFirst(new Object[3]);
	    lista.addFirst(new Object[4]);

	    lista.isOrdered();
	
	}
	
	@Test(expected = NullPointerException.class)
	public void testremoveNullPointerException() throws EmptyCollectionException {
		
		lSABC.remove(null);
		
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testremoveEmptyCollectionException() throws EmptyCollectionException {
		
		lS.remove("A");

		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testremoveNoSuchElementException() throws EmptyCollectionException {
		lSABC.remove("W");
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testremoveLastNullPointerException() throws EmptyCollectionException  {
		
		lSABC.removeLast(null);

		
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testremoveLastEmptyCollectionException() throws EmptyCollectionException {
		
		lS.removeLast("A");

		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testremoveLastNoSuchElementException() throws EmptyCollectionException {
		
		lSABC.removeLast("W");

	}
	
	@Test(expected = EmptyCollectionException.class)
	public void testgetFirstEmptyCollectionException() throws EmptyCollectionException {
		lS.getFirst();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testtoStringFromUntilIllegalArgumentExcceptionFromLessEqualZero() {
		lSABC.toStringFromUntil(0, 8);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testtoStringFromUntilIllegalArgumentExcceptionUntilLessEqualZero() {
		lSABC.toStringFromUntil(1, 0);

		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testtoStringFromUntilIllegalArgumentExcceptionUntilLessFrom() {
		lSABC.toStringFromUntil(20, 6);

		
	}
	
    //TEST DEL EQUALS CLASE PERSON
    
	@Test
	public void testEquals() {
		Person Perico = new Person("71623655J", "Perico", 19);
		Person Luisa = Perico;
		Person Juanita = new Person("71823655J", "Juana", 10);
		Person Paco = new Person("71623655J", "Paco", 79);
		int numero = 30;

		Assert.assertFalse(Perico.equals(Juanita));
		Assert.assertTrue(Luisa.equals(Perico));
		Assert.assertTrue(Paco.equals(Perico));
		Assert.assertFalse(Perico.equals(numero));

	}
    
    
    
    //TEST DEL COMPARETO CLASE PERSON
    
	@Test
	public void testCompareTo() {
		
		Person Perico = new Person("71623655J", "Perico", 19);
		Person Juanita = new Person("71823655J", "Juana", 10);
		Person Paco = new Person("71623655J", "Paco", 19);
		
		Assert.assertTrue(Perico.compareTo(Paco) == 0 );
		Assert.assertTrue(Perico.compareTo(Juanita) >0 );
		Assert.assertTrue(Juanita.compareTo(Perico) <0 );

	}
    
    
    
    /*TEST DE LA CLASE UNORDEREDLINKEDLISTIMPL*/
    
	@Test
	public void testaddLast() {
		Assert.assertEquals("(A B C )", lSABC.toString());
		lSABC.addLast("A");
		Assert.assertEquals("(A B C A )", lSABC.toString());

	}
	
	@Test
	public void testaddLastEmpty() {
		Assert.assertEquals("()", lS.toString());
		lS.addLast("A");
		Assert.assertEquals("(A )", lS.toString());

	}
	
	@Test
	public void testaddBefore() {
		Assert.assertEquals("(A B C )", lSABC.toString());
		lSABC.addBefore("A", "A");
		Assert.assertEquals("(A A B C )", lSABC.toString());
		lSABC.addBefore("A", "B");
		Assert.assertEquals("(A A A B C )", lSABC.toString());
		lSABC.addBefore("A", "C");
		Assert.assertEquals("(A A A B A C )", lSABC.toString());
	}
	
	@Test
	public void testaddBefore1Elem() {
		Assert.assertEquals("()", lS.toString());
		lS.addLast("A");
		lS.addBefore("Z", "A");
		Assert.assertEquals("(Z A )", lS.toString());
	}
	
    //TEST DE EXCEPCIONES

	@Test(expected = NoSuchElementException.class)
	public void testaddBefore1ElemNotContains() {
		Assert.assertEquals("()", lS.toString());
		lS.addLast("A");
		lS.addBefore("Z", "W");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testaddBefore1ElemNotContainsFull() {
		Assert.assertEquals("(A B C )", lSABC.toString());
		lSABC.addLast("A");
		lS.addBefore("Z", "W");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testaddBeforeEmpty() {
		Assert.assertEquals("()", lS.toString());
		lS.addBefore("Z", "W");
	}
	
	@Test(expected = NullPointerException.class)
	public void testaddBeforeNullPointerElem() {
		lS.addBefore(null, "B");
	}
	
	@Test(expected = NullPointerException.class)
	public void testaddBeforeNullPointerTarget() {
		lS.addBefore("A", null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testaddFirstNullPointerTarget() {
		lS.addFirst(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testaddLastNullPointerTarget() {
		lS.addLast(null);
	}
	
}
