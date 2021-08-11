package problem2;
import java.lang.Math;

public class Recursion {

	public static void main(String[] args) {
		//write a program that checks for the max element in an array, checks if a word is a palindrome 
		//and computes the number of zeroes in the binary representation of a number
		int[] arr = {1, 5, 6, 12, 45, 65, 45, 34, 35, 6};
		System.out.println("max element");
		System.out.println(binaryMax(arr, 0, arr.length-1));
		String word = "kayak";
		System.out.println("Is kayak a palindrome?");
		System.out.println(palindrome(word));
		System.out.println("Zeroes in binary representation of 322");
		System.out.println(zeros(322));
	}
	static int binaryMax(int [] x, int start, int end) {
		//binary recursive algorithm to find the max element of an array
		if(start == end) {
			return x[start];
		}
		int midpoint = (start + end)/2;
		int max1 = binaryMax(x, start, midpoint);
		int max2 = binaryMax(x, midpoint+1, end);
		if(max1>=max2) {
			return max1;
		}
		else {
			return max2;
		}
	}
	
	static int zeros(int num) {
		//algorithm that computes the number of zeros in the binary representation of a number recursively
		if(num<=2) {
			return log2(num);
		}
		int power = log2(num);
		int nextNum = num - ((int) Math.pow(2, power));
		int zeros = zeros(nextNum);
		int gap = power - log2(nextNum)-1;
		return zeros + gap;
		
	}
	static int log2(int n) {
		//logarithmic helper func
		int result = (int) (Math.log(n)/Math.log(2));
		return result;
	}
	static boolean palindrome(String word) {
		//Algorithm that computes whether a word is a palindrome recursively
		String letters = word.replaceAll("[\\W]", "");
		String[] arr = letters.toLowerCase().split("");
		return check(arr, 0, arr.length-1);
	}
	
	static boolean check(String[] word, int start, int end) {
		if(start == end) {
			return true;
		}
		if(start == end-1) {
			if(word[start].equals(word[end])) {
				return true;
			}
			else {return false;}
		}
		
		if(word[start].equals(word[end])) {
			return check(word, start+1, end-1);
		}
		else {return false;}
	}
}
