//
//	Name: Steven Phung
//	Project: 1
//	Due: 1/30/18
//	Course: cs-241-02-w18
//
//	Description:
//		Implementation of a binary search tree using node representation.
//
//		Class BinaryTree that contains methods that can be used to access or modify the binary tree.
//
package Project1_BinarySearchTree;

class BinaryTree {
	
	//Field that references the root node.
	BinaryNode rootNode;
	
	//Constructor that creates a root node as null.
	public BinaryTree() {
		rootNode = null;
	}
	
	//Function that recursively traverses the tree using pre-order: root node to left node to right node.
	public void preorderTraversal(BinaryNode node) {
		if(node != null) {
			System.out.print(node.getData() + " ");
			preorderTraversal(node.getLeftChild());
			preorderTraversal(node.getRightChild());
		}
	}

	//Function that recursively traverses the tree using in-order: left node to root node to right node.
	public void inorderTraversal(BinaryNode node) {
		if(node != null) {
			inorderTraversal(node.getLeftChild());
			System.out.print(node.getData() + " ");
			inorderTraversal(node.getRightChild());
		}
	}
	
	//Function that recursively traverses the tree using post-order: left node to right node to root node.
	public void postorderTraversal(BinaryNode node) {
		if(node != null) {
			postorderTraversal(node.getLeftChild());
			postorderTraversal(node.getRightChild());
			System.out.print(node.getData() + " ");
		}
	}
	
	//Function that calls the recursive add.
	public void add(int data) {
		rootNode = addEntry(rootNode, data);
	}
	
	//Function that adds in data recursively.
	private BinaryNode addEntry(BinaryNode node, int data) {
		BinaryNode newNode = new BinaryNode(data);
		//If there is no data present at root, one will be created.
		if(node == null) {
			node = newNode;
		}
		//Continues down chain of left or right children depending on values.
		if(data < node.getData()) {
			node.setLeftChild(addEntry(node.getLeftChild(), data));
		} else if(data > node.getData()) {
			node.setRightChild(addEntry(node.getRightChild(), data));
		}
		return node;
	}
	
	//Function that calls remove.
	public void remove(int data) {
		deleteEntry(rootNode, data);
	}
	
	//Function that recursively removes a node from an existing tree.
	public BinaryNode deleteEntry(BinaryNode node, int data) {
		//If there is no root to remove, nothing will be removed.
		if(node == null) {
			return null;
		}
		
		if(data == node.getData()) {
			//Case 1: Node has no children
			if(!node.hasLeftChild() && !node.hasRightChild()) {
				return null;
			}
			//Case 2: Node has 1 child, returns possible child to prevent loss of data.
			if(node.getRightChild() == null) {
				return node.getLeftChild();
			}	
			if(node.getLeftChild() == null) {
				return node.getRightChild();
			}
			//Case 3: Node has 2 children.
			//Gets left most value of right sub tree.
			int comparison = compareData(node.getRightChild());
			node.setData(comparison);
			node.setRightChild(deleteEntry(node.getRightChild(), comparison));
			return node;
		}
		//Recursively traverse tree's nodes
		if(data < node.getData()) {
			node.setLeftChild(deleteEntry(node.getLeftChild(), data));
			return node;
		}
		node.setRightChild(deleteEntry(node.getRightChild(), data));
		return node;
	}
	
	//Function that recursively retrieves the leftmost value.
	private int compareData(BinaryNode node) {
		int tempInt;
		if(!node.hasLeftChild()) {
			tempInt = node.getData();
		} else {
			tempInt = compareData(node.getLeftChild());
		}
		return tempInt;
	}
	
	//Function that recursively does an inorder traversal and uses an array to house both a predecessor and successor.
	public void inorderSearch(BinaryNode node, int data, BinaryNode[] binaryNodeArray) {
		//If there is no node then there is nothing to return.
		if(node == null) 
			return;
		//In an inorder traversal, we start with left child.
		inorderSearch(node.getLeftChild(), data, binaryNodeArray);
		//This will keep track of data based on current node.
		//If the node data matches with the search data, it will store the matched data.
		//If it has not yet reached a point where the data matches the search, it will continously store the next
		//iteration of data into an index in the array because until there is a match in data, the current number
		//has a chance to be the predecessor of the search key. It will stop storing in this specific index once
		//there is a match. Also once there is matching data, the remaining index of the array will store the final data
		//point after the search data, giving the successor.
		if(node.getData() == data) {
			binaryNodeArray[1] = node;
		} else if(binaryNodeArray[1] == null) {
			binaryNodeArray[0] = node;
		} else if(binaryNodeArray[2] == null) {
			binaryNodeArray[2] = node;
		}
		//If data does not match left child or root node of a subtree, it will move to the right child.
		inorderSearch(node.getRightChild(), data, binaryNodeArray);
	}
	
	//Function that returns true if there is no data in root node.
	public boolean isEmpty() {
		return rootNode == null;
	}
	
	//Function that sets the root node to null and essentially clears all successive references.
	public void clear() {
		rootNode = null;
	}
	
		//Nested BinaryNode specified for integers only.
	class BinaryNode {
		
		//Fields to reference data and either left or right child of a node.
		BinaryNode leftChild, rightChild;
		int data;
		
		//Constructor that takes no arguments and creates a node with integer 0.
		BinaryNode() {
			this(null, 0, null);
		}

		//Constructor that takes only an integer as its argument and does not have any children nodes.
		BinaryNode(int newData) {
			this(null, newData, null);
		}
		
		//Construct that sets data fields for data and both children.
		BinaryNode(BinaryNode leftChild, int data, BinaryNode rightChild) {
			setLeftChild(leftChild);
			setData(data);
			setRightChild(rightChild);
		}
		
		//Setter method that sets the leftChild.
		public void setLeftChild(BinaryNode leftChild) {
			this.leftChild = leftChild;
		}
		
		//Setter method that sets the data.
		public void setData(int data) {
			this.data = data;
		}
		
		//Setter method that sets the rightChild.
		public void setRightChild(BinaryNode rightChild) {
			this.rightChild = rightChild;
		}
		
		//Get method that retrieves the 'left' node of the current node.
		public BinaryNode getLeftChild() {
			return leftChild;
		}
		
		//Get method that retrieves the integer data from the node.
		public int getData() {
			return data;
		}
		
		//Get method that retrieves the 'right' node of the current node.
		public BinaryNode getRightChild() {
			return rightChild;
		}
		
		//Function that returns true if there is a left child.
		public boolean hasLeftChild() {
			return leftChild != null;
		}
		
		//Function that returns true if there is a right child.
		public boolean hasRightChild() {
			return rightChild != null;
		}
	}
}