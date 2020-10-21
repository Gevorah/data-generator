package datastructure;

public interface IAVL <K extends Comparable<K>,V> extends IABB <K,V>{

	public void insert(K key, V value);
	public void remove (K key);
	public void rebalance();
	public void rigthRotate();
	public void leftRotate();
	
}
