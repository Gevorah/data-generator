package datastructure;
import java.io.*;

public class Node<K extends Comparable<K>,V> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public K key;
	public V value;
	public Node<K,V> head, left, right;
	public int BFactor;

	public Node(Node<K,V> head, K key, V value) {
		this.head=head;
		this.key=key;
		this.value=value;
	}
	
	public Node(Node<K,V> head, K key, V value, int BFactor) {
		this.head=head;
		this.key=key;
		this.value=value;
		this.BFactor=BFactor;
	}
	public int isXChild(){
		if(head.left==this) return 1;
		if(head.right==this) return 2;
		return 0;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	
}
