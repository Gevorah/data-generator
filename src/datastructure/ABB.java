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
		if(current==null) return new Node<K,V>(key,value);
	    if(key.compareTo(current.getKey())<0) {
	        current.setLeft(insert(current.getLeft(),key,value));
	    } else if(key.compareTo(current.getKey())>0) {
	        current.setRight(insert(current.getRight(),key,value));
	    }
	    return current;
	}
	public void remove(K key) {
		root = remove(root,key);
	}
	private Node<K,V> remove(Node<K,V> current, K key) {
	    if(current==null) return null;
	    if(current.getKey().compareTo(key)==0) {
	    	if(current.getLeft()==null && current.getRight()==null) return null;
	    	if(current.getRight()== null) return current.getLeft();
	    	if(current.getRight() == null) return current.getRight();
	    	K minimum = findMinimum(current.getRight());
	    	current.setKey(minimum);
	    	current.setRight(remove(current.getRight(),minimum));
	    	return current;
	    } 
	    if(current.getKey().compareTo(key)>0) {
	        current.setLeft(remove(current.getLeft(),key));
	        return current;
	    } else {
	    	current.setRight(remove(current.getRight(),key));
	    	return current;
	    }
	}
	private K findMinimum(Node<K,V> root) {
	    return root.getLeft()==null?root.getKey():findMinimum(root.getLeft());
	}

	@Override
	public Node<K,V> getRoot() {return root;}

	@Override
	public V search(K key) {
		return search(root,key);
	}
	private V search(Node<K,V> current, K key) {
		if(current==null) return null;
		if(current.getKey().compareTo(key)==0) return current.getValue();
		return current.getKey().compareTo(key)>0?search(current.getLeft(),key):search(current.getRight(),key);
	}
	@Override
	public int getHeight() {
		
	}

	@Override
	public String inOrden() {
		
	}

	@Override
	public String postOrden() {
		
	}

	@Override
	public String preOrden() {
		
	}
}
