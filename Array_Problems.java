package problem1;

public class Arrays {
	
	public static void main(String[] args) {
		String in1 = "hello";
		String in2 = "o lleh";
		System.out.println(reversed(in1, in2));
		String[] a = {"Hello", "my", "is",  "name","Liam", "and", "I", "like", "dogs"};
		System.out.println(shortestThird(a));
		
	}
	public static int reversed(String in1, String in2) { 
		// Checks if inputs are the same string but reversed order. Runs in O(n)
		in1 = in1.replaceAll("\\s", "");
		in2 = in2.replaceAll("\\s", "");
		int n = in1.length();
		int valid = 1;
		if(n != in2.length()) {
			valid = 0;
			return valid;
		}
		String[] word1 = in1.toLowerCase().split("");
		String[] word2 = in2.toLowerCase().split("");
		for(int i =0; i<n; i++) {
			if(!word1[i].equals(word2[n-1-i])){
				valid=0;
				break;
			}
		}
		return valid;
	}
	
	public static String shortestThird(String[] phrase) { // 
		// Determine the word in every third of a phrase with the fewest characters. Runs in O(n^2)
		int n = phrase.length;
		int returnSize = n*1/3;
		String[] temp1 = new String[3];
		String[] temp2 = new String[returnSize];
		int j = 0;
		for(int i = 0; i<n; i++) {
			temp1[i%3] = phrase[i];
			if((i+1)%3 == 0) {
				temp2[j++] = shortest(temp1);
			}
		}
		String strang = "";
		for(int i = 0; i<temp2.length;i++) {
			strang += temp2[i]+" ";
		}
		return strang;
	}
	public static String shortest(String [] third) {
		String shortestString = "";
		int size = Integer.MAX_VALUE;
		for(String word: third) {
			if(word.length() < size) {
				size = word.length();
				shortestString = word;
			}
		}
		return shortestString;
	}
}
