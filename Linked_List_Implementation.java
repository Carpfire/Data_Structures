package exam;

public class Problem5 {
	//Linked List Implementation
	public static void main(String[] args) {
		LinkList ll = new LinkList();
		ll.insertFirst(99, "Uy");
		ll.insertFirst(87, "Copos");
		ll.insertFirst(100, "Pain");
		ll.insertFirst(88, "Espinosa");
		ll.insertFirst(60, "Sia");
		ll.sort();
		ll.displayList();
	}
}
class Link
	 {
	 public int iData;              
	 public String name; 
	 public Link next;              
	 public Link(int id, String myName) 
	    {
	    iData = id;
	    name = myName;
	    }
	 public void displayLink()      
	    {
	    System.out.print("{" + iData + ", " + name + "} ");
	    }
	 }  

class LinkList
	 {
	 private Link first;  
	 private int count = 0;


	 public LinkList()             
	    {
	    first = null;               
	    }

	 public void insertFirst(int id, String Name)
	    {                           
		   Link newLink = new Link(id, Name);
		   newLink.next = first;
		   first = newLink;
		   count ++;
	    
	    }
	 public void sort() {
		 if(first == null || first.next == null) {
			 return;
		 }
		 Link curr = first;
		 while(curr != null) {
			 Link iterator = curr;
			 Link min = iterator;
			 while(iterator != null) {
				 if(iterator.iData < min.iData) {
					 min = iterator;
				 }
				 iterator = iterator.next;
			 }
			 int temp1 = curr.iData;
			 String temp2 = curr.name;
			 curr.iData = min.iData;
			 curr.name = min.name;
			 min.iData = temp1;
			 min.name = temp2;
			 
			 curr = curr.next;
			 
		 }
	 }
	 
	 public int displayList()     
	    {
	    Link curr = this.first;
	    for(int i = 0; i< count; i ++) {
	    	curr.displayLink();
	    	curr = curr.next;
	    }

	    return this.count;
	    
	    }
	




	}


