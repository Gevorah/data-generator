package datastructure;

public class AVL<K extends Comparable<K>,V> implements IAVL<K,V>{

	public AVL() {
		
	}
	
	public void put(K key, V value, Node<K,V> current) {
		if(key.compareTo(current.key)<0) {
			if(current.left!=null) {
				put(key, value, current.left);
			}else {
				current.left = new Node<K,V>(current,key,value);
				balance(current.left);
			}
		}else {
			if(current.right!=null) {
				put(key, value, current.right);
			}else {
				current.right = new Node<K,V>(current,key,value);
				balance(current.right);
			}
		}

	}
	
	public void balance(Node<K,V> node) {
		if(node.BFactor>1 || node.BFactor<-1) {
			rebalance(node);
			return;
		}
		if(node.head!=null) {
			int x = node.isXChild();
			if(x==1) node.head.BFactor++;
			else if(x==2) node.head.BFactor--;
			if(node.head.BFactor!=0) balance(node.head);
		}
		
	}
	
	@Override
	public void rebalance(Node<K,V> node) {
		
		
	}

	@Override
	public void rigthRotate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftRotate() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Node<K, V> getRoot() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String inOrden() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String postOrden() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String preOrden() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}

}
