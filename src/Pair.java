

//every Letter has a value, check the java documentation 
//This class is necessary for the ArrayList
public class Pair<S,T>{
	private S first;
	private T second;
	
	public Pair(S firstEl, T secondEl){
		first= firstEl;
		second = secondEl;
	}
	
public S getFirst() { return first; }
public T getSecond() { return second; }

}