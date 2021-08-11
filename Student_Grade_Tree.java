package exam;

import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
 {
 public int iData;              // data item (key)
 public String name;           // data item
 public Node leftChild;         // this node's left child
 public Node rightChild;        // this node's right child

 public void display() {
	 System.out.print(this.iData + " ");
	 System.out.println(this.name);
 }
	 
 }  // end class Node  
////////////////////////////////////////////////////////////////
class Tree
 {
 private Node root;  
// first node of tree

//-------------------------------------------------------------
 public Tree()                  // constructor
    { root = null; }  
// no nodes in tree yet
//-------------------------------------------------------------
 public Node find(int key)      // find node with given key
    {                           // (assumes non-empty tree)
    Node curr = root;
    while(true) {
    if(curr.iData == key) {
    	return curr;
    	
    }
    if(key <= curr.iData) {
    	if(curr.leftChild == null) {
    		System.out.println("Node not found");
    		Node empty = new Node();
    		return empty;
    	}
    	curr = curr.leftChild;
    }
    else {
    	if(curr.rightChild==null) {
    		System.out.println("Node not found");
    		Node empty = new Node();
    		return empty;
    	}
    	curr = curr.rightChild;
    }
    }  // found it
    }  // end find()
 public Node findCeil(int key)      // find node with given key
 {
 Node before = root;// (assumes non-empty tree)
 Node curr = root;
 while(true) {
 if(curr.iData >= key && before.iData <= key) {
 	return curr; 	
 }
 
 before = curr;
 if(key <= curr.iData) {
 	if(curr.leftChild == null) {
 		System.out.println("Smallest Node");
 		return curr;
 	}
 	curr = curr.leftChild;
 }
 else {
 	if(curr.rightChild==null) {
 		System.out.println("No Grades at this Level");
 		Node empty = new Node();
 		return empty;
 	}
 	curr = curr.rightChild;
 }
 }  // found it
 }  // end find()
 public Node find(String name) {
	 return preOrder(root, name);
 }
 
 public Node find(int ikey, String myName)      // find node with given key
 {                           // (assumes non-empty tree)
 Node curr = root;
 while(true) {
 if(curr.iData == ikey && myName.equals(curr.name)) {
 	return curr;
 	
 }
 
 if(ikey <= curr.iData) {
 	if(curr.leftChild == null) {
 		System.out.println("Node not found");
 		Node empty = new Node();
 		return empty;
 	}
 	curr = curr.leftChild;
 }
 else {
 	if(curr.rightChild==null) {
 		System.out.println("Node not found");
 		Node empty = new Node();
 		return empty;
 	}
 	curr = curr.rightChild;
 }
 }  // found it
 } 
//-------------------------------------------------------------
 public void insert(int id, String myName) //this method inserts a node of (id and dd) into the tree. (We are consider a BINARY SEARCH TREE by iData)
    {
	 Node newNode = new Node();
	 newNode.iData=id;
	 newNode.name=myName;
	 if(root == null) {
		 root = newNode;
		 return;
	 }
	 Node curr = root;
	 while(true) {
		 if(newNode.iData <= curr.iData) {
			 if(curr.leftChild == null) {
				 curr.leftChild = newNode;
				 return;
			 }
			 curr = curr.leftChild;
		 }
		 else{
			 if(curr.rightChild==null) {
				 curr.rightChild = newNode;
				 return;
			 }
			 curr = curr.rightChild;
		 }
	 }
   }
   
 public void gradesGT(int grade) {
	// Node localRoot = findCeil(grade);
	 inOrder(root, grade);
 }
 
 private Node preOrder(Node localRoot, String name) //implement preOrder traversal
    {
	 Node found = null;
	 if(localRoot==null) {
//		 localRoot.display();
		 return found;
		 }
	 Node n1 = preOrder(localRoot.leftChild, name);
	 Node n2 = preOrder(localRoot.rightChild, name);
	 if(name.equals(localRoot.name)) {
		 found = localRoot;
	 }
	 if(n1 != null) {
		 found = n1;
	 }
	 if(n2 != null) {
		 found = n2;
	 }
	 return found;
   

    }

 public void Sort() {
	 inOrder(root);
 }
//-------------------------------------------------------------
 private void inOrder(Node localRoot) //implement in Order traversal
    {
	 if(localRoot==null) {
		 return;
		 }
	 inOrder(localRoot.leftChild);
	 localRoot.display();
	 inOrder(localRoot.rightChild);
	 return;
 
    }
 private void inOrder(Node localRoot, int cutoff) //implement in Order traversal
 {
	 if(localRoot==null) {
		 return;
		 }
	 inOrder(localRoot.leftChild, cutoff);
	 inOrder(localRoot.rightChild, cutoff);
	 if(localRoot.iData > cutoff) {
		 localRoot.display();
	 }
	 
	 return;

 }



 public boolean delete(String name) // delete node with given key (iData) (if there are multiple nodes match key with iData you have to delete all of them.
    {
	 Node foundNode = find(name);
	 int key = foundNode.iData;
	 boolean found  = false;// (assumes non-empty list)
	 boolean keyExists = false;
	 if(find(key, name).iData == key && name.equals(find(key,name).name)) {
		 keyExists = true;
	 }
	 while(keyExists) {
		 Node curr = root;
		 Node par = root;
		 boolean isLeft = false;
		 
		 while(curr.iData != key || !name.equals(curr.name)) {
			 curr.display();
			 par = curr;
			 if(key<=curr.iData) {
				 curr = curr.leftChild;
				 isLeft = true;
			 }
			 
			 else {
				 isLeft = false;
				 curr = curr.rightChild;
			 }
			 if(curr == null) {
				 return found;
			
		 }
		 }
			 found = true;
			 keyExists = false;
			 if(curr.rightChild==null && curr.leftChild==null) {
				 if(curr == root) {
					 root = null;
					 return found;
				 }
				 else if(isLeft) {
					 par.leftChild = null;
					 return found;
				 }
				 else
				 {
					 par.rightChild = null;
					 return found;
				 }
	
			 }
			 else if(curr.rightChild == null) {
				 if(curr == root) {
					 root = curr.leftChild;
				 }
				 else if(isLeft) {
					 par.leftChild = curr.leftChild;
				 }
				 else {
					 par.rightChild = curr.leftChild;
				 }
			 }
			 else if(curr.leftChild == null) {
				 if(curr == root) {
					 root = curr.rightChild;
				 }
				 else if(isLeft) {
					 par.leftChild = curr.rightChild;
				 }
				 else {
					 par.rightChild = curr.rightChild;
				 }
			 }
			 else {
				 Node before = curr;
				 Node pred = curr;
				 int i = 0;
				 pred = pred.leftChild;
				 while(pred.rightChild != null) {
					 if(i == 0) {
						 before = before.leftChild;
					 }
					 if(i!=0) {
						 before = before.rightChild;
					 }
					 i++;
					 pred = pred.rightChild;
				 }
				 
				 if(curr == root) {
					 root.iData = pred.iData;
					 root.name = pred.name;
					 if(pred.leftChild!= null) {
						 before.rightChild = pred.leftChild;
					 }
					 else {
						 if(i==0) {
							 before.leftChild = null;
						 }
						 else {
							 before.rightChild = null;
						 }
					 }
				 }
				 else if(isLeft) {
					 pred.rightChild = curr.rightChild;
					 par.leftChild = pred;
				 }
				 else {
					 pred.rightChild = curr.rightChild;
					 par.rightChild = pred;
					 }
			 }
			 
	 }
			 return found;
		 }
	 
    		
 
		
    
 
 

	



                  

   // end delete()

//-------------------------------------------------------------
 public void displayTreeLevels() // this method will display the nodes at each level in the tree. (The method should print the nodes (id) as: Level1:.... - Level2:... 
    {
	 Stack globalStack = new Stack();
	 boolean rowEmpty = false;
	 globalStack.push(root);
	 int i = 0;
	 while(rowEmpty==false) {
		 i++;
		 rowEmpty = true;
		 Stack localStack = new Stack();
		 
		 System.out.print("Level ");
		 System.out.println(i);
		 while(globalStack.isEmpty()==false) {
			 Node curr = (Node)globalStack.pop();
			 if(curr != null) {
				 System.out.print(curr.name+ ": ");
				 System.out.print(curr.iData);
				 System.out.print(" ");
				 if(curr.leftChild != null) {
					Node left = curr.leftChild;
					localStack.push(left);
					rowEmpty = false;
				 }
				 if(curr.rightChild != null) {
					 Node right = curr.rightChild;
					 localStack.push(right);
					 rowEmpty = false;
				 }
				 
				 }
		 }
		 System.out.println();
		 while(localStack.isEmpty() == false) {
			 globalStack.push(localStack.pop());
		 }

			 
		 }
		 
	 }
 }
	 
	 






      // end displayTreeLevels()








public class Problem3 {
	public static void main(String[] args) {
		 int value;

		    Tree theTree = new Tree();

		     //... you change these inputs to build the tree, and/or can add other inputs to test the program. 
		    //The tree is ordered by iData.  


		    
		    while(true) {
		    	Scanner keyboard = new Scanner(System.in);
		    	System.out.println("Welcome to Student Tree, what would you like to do?\n"
		    			+ "1 Grade Name x 10: Create BST. arg is 10 Student Grade Pairs. (91 John 15 Emily )\n"
		    			+ "2 Grade Name: Insert New Student\n"
		    			+ "3 Name: Delete Student\n"
		    			+ "4 Name: Search by Name and Display Info\n"
		    			+ "5 Display Students Sorted by Grade\n"
		    			+ "6 (Cutoff Grade): Display Students above cutoff grade.\n"
		    			+ "7: Display Tree Levels\n"
		    			+ "8: Exit Program");
		    	int arg1 = keyboard.nextInt();
		    	int [] grades = new int[10];
	    		String [] names = new String[10];	
		    	if(arg1 == 1) {
		    		int i =0;
		    		while(i < 10) {
		    			int grade = keyboard.nextInt();
		    			String name = keyboard.next();
		    			grades[i] = grade;
		    			names[i] = name;
		    			i++;
		    		}
		    	}
		    	int arg2 = 0;
		    	String arg3="";
		    	if(arg1==6) {
		    		arg2 = keyboard.nextInt();
		    	}
		    	if(arg1 == 3|| arg1 == 4) {
		    		arg3 = keyboard.next();
		    	}
		    	if( arg1 == 2) {
		    		arg2 = keyboard.nextInt();
		    		arg3 = keyboard.next();
		    	}
		    	switch(arg1) {
		    		case 1:
		    			for(int i = 0; i<10; i++) {
		    				int g = grades[i];
		    				String n = names[i];
		    				theTree.insert(g, n);
		    			}
		    			break;		    			
		    		case 2:
		    			theTree.insert(arg2, arg3);
		    			break;
		    		case 3: 
		    			theTree.delete(arg3);
		    			break;
		    		case 4:
		    			Node n = theTree.find(arg3);
		    			if(n != null) {
		    				n.display();
		    			}
		    			else {System.out.println("Not Found");}
		    			break;
		    		case 5:
		    			theTree.Sort();
		    			break;
		    		case 6:
		    			theTree.gradesGT(arg2);
		    			break;
		    		case 7:
		    			theTree.displayTreeLevels();
		    			break;
		    		case 8: 
		    			System.exit(0);
		    			break;
		    			
		    	}
		    			
		    	
		    }
	}

}
