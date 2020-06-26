package ule.edi.model;

public class Person implements Comparable<Person>{ //como implementa el interfaz comparable tenemos disponible el compareTo

	private String name;
	private String nif;
	
	private int age;
	
	

	public Person(String nif, String name, int edad) {
        this.nif=nif;
		this.name = name;
		this.age = edad;
		
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "{"+nif + ", " + name + ", " + age + "}";
	}
	
    @Override
	public boolean equals(Object obj) {
		// Dos personas son iguales si son iguales sus nifs
    	//Lo primero compruebo si las flechas apuntan a la misma direccion de memoria (en cuyo caso seria el mismo objeto)
    	if(this == obj) {
    		return true;
    	}
    	else if(obj instanceof Person){ //Miro si son de la mimsma clase (si el obj es de la misma clase del this, que es esta)
    		//si no son la misma flecha aun cabe la posibilidad de que sean copias iguales (mismo objeto pero en distintas posisiones de memoria)
    		Person convertido = (Person)obj;
    		
    		if(this.nif.compareTo(convertido.nif) == 0) { //como lo son, miro sus NIF a ver si son iguales
    			  return true;
    			
    		}
    	
    	}
    	return false; //si no son la misma flecha y no son de la misma clase o son de la misma clase pero no coincide el nif, retorna que no son iguales
    }


	@Override
	public int compareTo(Person o) {
		// Las personas se comparan por la edad.
		if(this.age == o.age) {
			//Si ambas personas tienen la misma edad, es que sin iguales
			return 0;
		}
		else {
			//si no, es que una de las dos es mas grande que la otra, pero como lo que retorna el compare es >0 o <0, en ambos dos casos la diferencia se hace igual solo que en un caso queda positivo y en el otro negativo
			return this.age - o.age; //el que comparo simepre es el this
				
		}
			
	}
	
}
