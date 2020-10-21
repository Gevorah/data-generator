package datastructure;

public interface  IABB<K extends Comparable<K>, V>{

	public void insert(K key, V value);
	public void remove (K key);
	public Node <K,V> getRoot();
	public V search (K key);
	public int getHeight();
	public String inOrden();
	public String postOrden ();
	public String preOrden();
	
}
