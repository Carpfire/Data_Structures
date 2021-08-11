package justhashing;




class DataEntry{
	//Data Field Class for the Hash Table. Simple Key Value Pair
	private int key;
	
	public DataEntry(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return key;
	}
	
	public void display() {
		System.out.println(key);
	}
}

class HashTable{
	private int size;
	private DataEntry [] hashArray;
	private DataEntry empty;
	private int entries;
	
	public HashTable(int size) {
		this.size = size;
		hashArray = new DataEntry [size];
		empty = new DataEntry(-1);
	}
	
	public int hashFunc(int key) {
		return key%size;
	}
	public void displayTable() {
		for(int i = 0; i < size; i++) {
			if(hashArray[i] != null) {
				System.out.println(hashArray[i].getKey());
		}
	}
	}
	
	public void insert(int key) {
		if(entries == size) {
			System.out.println("Hash Table Full");
			return;
			}
		entries++;
		DataEntry dd = new DataEntry(key);
		int hashVal = hashFunc(key);
		while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
			hashVal++;
			hashVal %= size;
		}
		hashArray[hashVal] = dd;
	}
	
	public DataEntry search(int key) {
		int hashVal = hashFunc(key);
		int iter = 0;
		while(hashArray[hashVal].getKey() != key) {
			hashVal++;
			hashVal %= size;
			iter ++;
			if(iter == size) {
				System.out.println("Element Not Found");
				return null;
			}
		}
		
		return hashArray[hashVal];
	}
	
	public DataEntry delete(int key) {
		int hashVal = hashFunc(key);
		if(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
			while(hashArray[hashVal].getKey() != key) {
				hashVal++;
				hashVal %= size;
			}
			while(hashArray[hashVal].getKey() == hashArray[hashVal + 1 % size].getKey()) {
				hashVal++;
				hashVal %= size;
			}
			DataEntry temp = hashArray[hashVal];
			hashArray[hashVal] = empty; 
			return temp;
		}
		return null;
	}
}



public class HashingItUp {
	
	

	public static void main(String[] args) {
		HashTable theTable = new HashTable(5);
		theTable.insert(5);
		theTable.insert(5);
		theTable.insert(1);
		theTable.insert(4);
		theTable.insert(6);
		theTable.displayTable();
		theTable.search(40);
		theTable.displayTable();
		
		

	}

}