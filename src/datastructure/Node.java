package datastructure;

public class Node<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private Node<K,V> left, right;
	private V BFactor;
	
	public Node(K key, V value) {
		this.key=key;
		this.value=value;
	}
	
	public Node(K key, V value, V BFactor) {
		this.key=key;
		this.value=value;
		this.BFactor= BFactor;
	}
	public K getKey() {return key;}
	public V getValue() {return value;}
	public void setKey(K key) {this.key=key;}
	public void setValue(V value) {this.value=value;}
	public Node<K,V> getLeft(){return left;}
	public Node<K,V> getRight(){return right;}
	public void setLeft(Node<K,V> left){this.left=left;}
	public void setRight(Node<K,V> right){this.right=right;}
}
