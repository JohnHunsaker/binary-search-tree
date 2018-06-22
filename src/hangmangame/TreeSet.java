
package hangmangame;

/**
 * Class to model a TreeSet.
 * @author John H
 */

public class TreeSet {
	
	/**
	 * Private class to represent a node.
	 */
	private class Node {
		char mKey;
		Node mLeft;
		Node mRight;
		Node mParent;
		
		/**
		 * Constructor for Node class.
		 * @param key 
		 */
		public Node(char key) {
			mKey = key;
			mLeft = null;
			mRight = null;
			mParent = null;
		}
		
		/**
		 * Mutator for Node class.
		 * @param parent 
		 */
		public void setParent(Node parent) {
			mParent = parent;
		}
		
		/**
		 * Mutator for Node class.
		 * @param left 
		 */
		public void setLeft(Node left) {
			mLeft = left;
		}
		
		/**
		 * Mutator for Node class.
		 * @param right 
		 */
		public void setRight(Node right) {
			mRight = right;
		}
		
		/**
		 * Mutator for Node class.
		 * @param key 
		 */
		public void setData(char key) {
			mKey = key;
		}
	}
	
	Node mRoot;
	int mCount;
	
	/**
	 * Constructor.
	 */
	public TreeSet() {
		mRoot = null;
		mCount++;
	}
	
	/**
	 * Method recursively locates and returns the Node which contains the 
	 * specified key in the tree below and including n. Returns null if no 
	 * such node exists.
	 * @param key
	 * @param n
	 * @return 
	 */
	private Node findKey(char key, Node n) {
		if (n == null) {
			return null;
		}
		else if (n.mKey == key) {
			return n;
		}
		else if (key < n.mKey) {
			return findKey(key, n.mLeft);
		}
		else {
			return findKey(key, n.mRight);
		}
	}
	
	/**
	 * Method recursively finds and returns the Node with the largest key in 
	 * the tree below and including n.
	 * @param n
	 * @return 
	 */
	private Node findMaximum(Node n) {
		if (n.mRight == null) {
			return n;
		}
		else {
			return findMaximum(n.mRight);
		}
	}
	
	/**
	 * Method recursively prints the Node called n and its children
	 * using an in-order traversal.
	 * @param n 
	 */
	private void printInOrder(Node n) {
		if (n != null) {
			printInOrder(n.mLeft);
			printInOrder(n.mRight);
			System.out.println("   " + n.mKey);
		}
	}
	
	/**
	 * Method adds the key to the set.
	 * @param key 
	 */
	public void add(char key) {
		if (mRoot == null) {
			Node newNode = new Node(key);
			mRoot = newNode;
		}
		
		else {
			addAt(key, mRoot);
		}
	}
	
	/**
	 * Method recursively adds the key to the correct position below the node.
	 * @param key
	 * @param n 
	 */
	private void addAt(char key, Node n) {
		if (key == n.mKey) {
			return;
		}
		
		else if (key < n.mKey) {
			if (n.mLeft == null) {
				Node newNode = new Node(key);
				n.setLeft(newNode);
				newNode.setParent(n);
				mCount++;
			}
			else {
				addAt(key, n.mLeft);
			}
		}
		else {
			if (n.mRight == null) {
				Node newNode = new Node(key);
				n.setRight(newNode);
				newNode.setParent(n);
				mCount++;
			}
			
			else {
				addAt(key, n.mRight);
			}
		}
	}
	
	/**
	 * Method removes the key from the set.
	 * @param key 
	 */
	public void remove(char key) {
		removeAt(key, mRoot);
	}
	
	/**
	 * Method helps remove method by locating the key node position.
	 * @param key
	 * @param n 
	 */
	private void removeAt(char key, Node n) {
		if (n == null) {
			throw new NullPointerException("Error: Tree has nothing in it!");
		}
		else if (key < n.mKey) {
			removeAt(key, n.mLeft);
		}
		else if (key > n.mKey) {
			removeAt(key, n.mRight);
		}
		else {
			removeNode(n);
		}
	}
	
	/**
	 * Method helps remove and removeAt methods by actually doing the work
	 * of removing the node.
	 * @param n 
	 */
	private void removeNode (Node n) {
		
		//0 Children Case
		if (n.mLeft == null && n.mRight == null) {
			if (n == mRoot) {
				mRoot = null;
			}
			else if (n.mParent.mLeft == n) {
				n.mParent.setLeft(null);
			}
			else {
				n.mParent.setRight(null);
			}
			mCount--;
		}
		
		//1 Child Case
		else if (n.mLeft == null || n.mRight == null) {
			if (n == mRoot) {
				if (n.mLeft != null) {
					mRoot = n.mLeft;
					n.setParent(null);
				}
				else {
					mRoot = n.mRight;
					n.setParent(null);
				}
			}
			else {
				//Parent Left
				if (n.mParent.mLeft == n) {
					if (n.mLeft != null) {
						n.mParent.setLeft(n.mLeft);
						n.mLeft.setParent(n.mParent);
					}
					else {
						n.mParent.setLeft(n.mRight);
						n.mRight.setParent(n.mParent);
					}
				}
				//Parent Right
				else {
					if (n.mRight != null) {
						n.mParent.setRight(n.mRight);
						n.mRight.setParent(n.mParent);
					}
					else {
						n.mParent.setRight(n.mLeft);
						n.mLeft.setParent(n.mParent);
					}
				}
			}
			mCount--;
		}
		
		//2 Children Case
		else {
			Node maxNode = findMaximum(n.mLeft);
			n.setData(maxNode.mKey);
			removeNode(maxNode);
		}
	}
	
	/**
	 * Method clears the set so it is empty.
	 */
	public void clear() {
		mRoot = null;
		mCount = 0;
	}

	/**
	 * Method returns true if the set contains the given key.
	 * @param key
	 * @return 
	 */
	public boolean find(char key) {
		Node testNode = mRoot;
		
		while (testNode != null) {
			if (testNode.mKey == key) {
				return true;
			}
			else if (testNode.mKey > key) {
				testNode = testNode.mLeft;
			}
			else {
				testNode = testNode.mRight;
			}
		}
		return false;
	}
	
	/**
	 * Method returns the number of keys in the tree.
	 * @return 
	 */
	public int getCount() {
		if (mCount == 1) {
			return 0;
		}
		else {
			return mCount;
		}
	}
	
	/**
	 * Method returns the height of the tree, calculated recursively.
	 * @return 
	 */
	public int getHeight() {
		if (getHeight(mRoot) == 0) {
			return 0;
		}
		else {
			return getHeight(mRoot) - 1;
		}
	}
	
	/**
	 * Method recursively calculates the height of the sub-tree starting at n.
	 * @param n
	 * @return 
	 */
	private int getHeight(Node n) {
		
		if (n == null) {
			return 0;
		}

		int hLt = getHeight(n.mLeft);
		int hRt = getHeight(n.mRight);

		if (hLt > hRt) {
			return hLt += 1;
		}
		else {
			return hRt += 1;
		}
	}
	
	/**
	 * Method prints all the keys in the tree using an in-order traversal. 
	 * Calls the recursive private helper method printInOrder starting 
	 * at the tree's Root.
	 */
	public void printAll() {
		printInOrder(mRoot);
	}

	/**
	 * Method given in prompt to print the tree structure.
	 */
	public void printTreeStructure() {
		java.util.LinkedList<Node> list = new java.util.LinkedList<Node>();
      java.util.LinkedList<Integer> spaces = 
       new java.util.LinkedList<Integer>();
      list.add(mRoot);
      spaces.add(0);

      while (!list.isEmpty()) {
			Node n = list.removeLast();
         int s = spaces.removeLast();
        
         for (int i = 0; i < s; i++) {
            System.out.print(" ");
         }
         if (n != null)
            System.out.println(n.mKey);
         else 
            System.out.println("-");
         
         if (n != null && (n.mRight != null || n.mLeft != null)) {
            list.addLast(n.mRight);
            spaces.addLast(s + 3);
            list.addLast(n.mLeft);
            spaces.addLast(s + 3);
         }
      }
   }
}