package datastructure;

public interface IAVL<K extends Comparable<K>,V> extends IABB<K,V>{
	public void rebalance(Node<K,V> node);
	public void leftRotate(Node<K,V> node);
	public void rigthRotate(Node<K,V> node);	
}
