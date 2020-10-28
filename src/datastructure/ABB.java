package datastructure;

public class ABB<K extends Comparable<K>,V> implements IABB<K,V> {
	private Node<K,V> root;

	public ABB() {
		root = null;
	}

	public void insert(K key, V value) {
		root = insert(root,key,value);
	}
	
	private Node<K,V> insert(Node<K,V> current, K key, V value) {
		if(current==null) return new Node<K,V>(null,key,value);
	    if(key.compareTo(current.key)<0) {
	        current.left=insert(current.left,key,value);
	    } else if(key.compareTo(current.key)>0) {
	        current.right=insert(current.right,key,value);
	    }
	    return current;
	}
	public void remove(K key) {
		root = remove(root,key);
	}
	private Node<K,V> remove(Node<K,V> current, K key) {
	    if(current==null) return null;
	    if(current.key.compareTo(key)==0) {
	    	if(current.left==null && current.right==null) return null;
	    	if(current.left== null) return current.left;
	    	if(current.right == null) return current.right;
	    	K minimum = findMinimum(current.right);
	    	current.key=minimum;
	    	current.right=remove(current.right,minimum);
	    	return current;
	    } 
	    if(current.key.compareTo(key)>0) {
	        current.left=remove(current.left,key);
	        return current;
	    } else {
	    	current.right=remove(current.right,key);
	    	return current;
	    }
	}
	private K findMinimum(Node<K,V> root) {
	    return root.left)==null?root.key:findMinimum(root.left);
	}

	@Override
	public Node<K,V> getRoot() {return root;}

	@Override
	public V search(K key) {
		return search(root,key);
	}
	private V search(Node<K,V> current, K key) {
		if(current==null) return null;
		if(current.key.compareTo(key)==0) return current.value;
		return current.key.compareTo(key)>0?search(current.left,key):search(current.right,key);
	}
	@Override
	public int getHeight() {
		return 0;
		
	}

	@Override
	public String inOrden() {
		return null;
		
	}

	@Override
	public String postOrden() {
		return null;
		
	}

	@Override
	public String preOrden() {
		return null;
		
	}
}
