package exam;
import java.util.Scanner;
import java.util.Stack;
public class ExtraCredit {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your postfix expression(space seperated) and type e to evaluate:");
		Stack<Integer> myStack = new Stack<Integer>();
		while(keyboard.hasNext()) {
			while(keyboard.hasNextInt()) {
				int num = keyboard.nextInt();
				System.out.print("Pushed: ");
				System.out.println(num);
				myStack.push(num);
			}
			String op = keyboard.next();
			System.out.println(op);
			if(op.equals("e")) {
				break;
			}
			int n1 = myStack.pop();
			int n2 = myStack.pop();
			switch (op){
				case "+":
					myStack.push(n2 + n1);
					break;
				case "-":
					myStack.push(n2 - n1);
					break;
				case "*":
					myStack.push(n2 * n1);
					break;
				case "/":
					myStack.push(n2/n1);
					break;
			}
			
		}
		System.out.println(myStack.pop());

	}

}
