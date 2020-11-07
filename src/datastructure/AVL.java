package datastructure;
import java.io.*;

public class AVL<K extends Comparable<K>,V> implements IAVL<K,V> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Node<K,V> root;
	
	public AVL() {
		root=null;
	}
	
	public void put(K key, V value) {
		if(root==null)root=new Node<K,V>(null,key,value);
		else put(key,value,root);
	}
	private void put(K key, V value, Node<K,V> current) {
		if(key.compareTo(current.key)<0) {
			if(current.left!=null) put(key, value, current.left);
			else {
				current.left = new Node<K,V>(current,key,value);
				balance(current.left);
			}
		}else {
			if(current.right!=null)put(key, value, current.right);
			else {
				current.right = new Node<K,V>(current,key,value);
				balance(current.right);
			}
		}
	}
	
	public void remove(K key) {
		remove(key,root);
	}
	private Node<K,V> remove(K key, Node<K,V> current) {
	    if(current==null) return null;
	    if(current.key.compareTo(key)==0) {
	    	if(current.left==null && current.right==null) return null;
	    	if(current.left== null) return current.left;
	    	if(current.right == null) return current.right;
	    	K minimum = findMinimum(current.right);
	    	current.key=minimum;
	    	current.right=remove(minimum,current.right);
	    	return current;
	    } 
	    if(current.key.compareTo(key)>0) {
	        current.left=remove(key,current.left);
	        return current;
	    } else {
	    	current.right=remove(key,current.right);
	    	return current;
	    }
	}
	private K findMinimum(Node<K,V> root) {
	    return root.left==null?root.key:findMinimum(root.left);
	}
	
	public V search(K key) {
		return search(key,root);
	}
	public V search(K key, Node<K,V> current) {
		if(current==null) return null;
		if(current.key.compareTo(key)==0) return current.value;
		return current.key.compareTo(key)>0?search(key,current.left):search(key,current.right);
	}
	
	public void balance(Node<K,V> node) {
		if(node.BFactor>1 || node.BFactor<-1) rebalance(node);
		else if(node.head!=null) {
			int x = node.isXChild();
			if(x==1) node.head.BFactor++;
			else if(x==2) node.head.BFactor--;
			if(node.head.BFactor!=0) balance(node.head);
		}
	}
	
	public void rebalance(Node<K,V> node) {
		if(node.BFactor<0) {
			if(node.right.BFactor>0)rigthRotate(node.right);
			leftRotate(node);
		}else if(node.BFactor>0) {
			if(node.left.BFactor<0)leftRotate(node.left);
			rigthRotate(node);
		}	
	}
	
	@Override
	public void leftRotate(Node<K,V> f) {
		Node<K,V> nroot = f.right;
		f.right = nroot.left;
		if(nroot.left!=null)nroot.left.head=f;
		nroot.head=f.head;
		if(f.head==null)root=nroot;
		else {
			int x = f.isXChild();
			if(x==1)f.head.left=nroot;
			else if(x==2)f.head.right=nroot;
		}
		nroot.left=f;
		f.head=nroot;
		f.BFactor=f.BFactor+1 - Math.min(nroot.BFactor, 0);
		nroot.BFactor=nroot.BFactor+1 + Math.max(f.BFactor, 0);
	}
	
	@Override
	public void rigthRotate(Node<K,V> f) {
		Node<K,V> nroot = f.left;
		f.left = nroot.right;
		if(nroot.right!=null)nroot.right.head=f;
		nroot.head=f.head;
		if(f.head==null)root=nroot;
		else {
			int x = f.isXChild();
			if(x==1)f.head.left=nroot;
			else if(x==2) f.head.right=nroot;
		}
		nroot.right=f;
		f.head=nroot;
		f.BFactor=f.BFactor-1 - Math.max(nroot.BFactor, 0);
		nroot.BFactor=nroot.BFactor-1 + Math.min(f.BFactor, 0);
	}

	@Override
	public Node<K, V> getRoot() {
		return root;
	}
	
	public static String way;
	@Override
	public void inOrden(Node<K,V> node) {
		if(node!=null) {
			inOrden(node.left);
			way+= node.value.toString()+", ";
			inOrden(node.right);
		}
	}

	@Override
	public void preOrden(Node<K,V> node) {
		if(node!=null) {
			way+= node.value+", ";
			inOrden(node.left);
			inOrden(node.right);
		}
	}

	@Override
	public void postOrden(Node<K,V> node) {
		if(node!=null) {
			inOrden(node.left);
			inOrden(node.right);
			way+= node.value+", ";
		}
	}
}
