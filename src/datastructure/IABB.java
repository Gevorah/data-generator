package datastructure;

public interface  IABB<K extends Comparable<K>, V>{

	public void put(K key, V value);
	public void remove (K key);
	public V search (K key);
	public Node <K,V> getRoot();
	public void inOrden(Node<K,V> node);
	public void preOrden(Node<K,V> node);
	public void postOrden(Node<K,V> node);
	
}
