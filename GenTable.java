/*
 * xharrym
 * 2014
 *
 * Generates truth table from function
 * Check if function are equivalent
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class GenTable {
	public char[] function;
	public int nvar;
	public int ninstruction;
	public boolean [] currentVar;

	public GenTable(){
		String inputFunction = new String();
		Scanner input = new Scanner(System.in);

		System.out.println("Input the number of variables: ");
		nvar = input.nextInt();
		currentVar = new boolean[nvar];

		System.out.println("Input the function: ");
		inputFunction = input.next();
		ninstruction = inputFunction.length();
		function = new char[ninstruction];
		function = inputFunction.toCharArray();

	}

	public void setCurrent(int n){
		int i;
		for(i = nvar-1; i>=0;i--){
			if(n%2 == 0)
				currentVar[i] = false;
			else
				currentVar[i] = true;
			n = n>>1;

		}

	}

	public void calculate(){
		int i,n;
		BoolStack myStack = new BoolStack();

		firstLine();

		for(n=0;n<Math.pow(2, nvar);n++){
			setCurrent(n);

			for(i=0;i<ninstruction;i++){
				switch(function[i]){
					case '|':	myStack.or();
								break;
					case '&':	myStack.and();
								break;
					case '!': 	myStack.not();
								break;

					default:	myStack.push(currentVar[(int)function[i]-97]);
				}
			}

			print(myStack.returnResult());
			myStack.reset();
		}
	}

	public void firstLine(){
		int i;

		String first = new String();
		first = "Function: " + String.valueOf(function) + "\n\n";
		for(i=0;i<nvar;i++)
			first = first + (char)(i+97) + "\t";
		first = first + "\tY\n";

		System.out.println(first);

		try {
			FileWriter fw = new FileWriter("table.txt");
			fw.write(first);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void print(boolean res){
		String line = new String();
		int i;
		for(i=0;i<nvar;i++){
			if (currentVar[i])
				line = line +"1\t";
			else
				line = line +"0\t";
		}
		if(res)
			line = line +"\t1";
		else
			line = line +"\t0";

		System.out.println(line);

		try {
			FileWriter fw = new FileWriter("table.txt",true);
			fw.write(line+"\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void checkFunction(){
		int i1,i2,n;
		BoolStack myStack1 = new BoolStack();
		BoolStack myStack2 = new BoolStack();
		String inputFunction2 = new String();
		Scanner input = new Scanner(System.in);

		System.out.println("Input the second function: ");
		inputFunction2 = input.next();
		int ninstruction2 = inputFunction2.length();
		char [] function2 = new char[ninstruction];
		function2 = inputFunction2.toCharArray();

		boolean different = false;

		for(n=0;n<Math.pow(2, nvar);n++){
			setCurrent(n);

			for(i1=0;i1<ninstruction;i1++){
				switch(function[i1]){
					case '|':	myStack1.or();
								break;
					case '&':	myStack1.and();
								break;
					case '!': 	myStack1.not();
								break;

					default:	myStack1.push(currentVar[(int)function[i1]-97]);
				}
			}

			for(i2=0;i2<ninstruction2;i2++){
				switch(function2[i2]){
					case '|':	myStack2.or();
								break;
					case '&':	myStack2.and();
								break;
					case '!': 	myStack2.not();
								break;

					default:	myStack2.push(currentVar[(int)function2[i2]-97]);
				}
			}

			if(myStack1.returnResult() != myStack2.returnResult()){
				System.out.println("The two functions are NOT equivalent!");
				different = true;
				break;
			}
			myStack1.reset();
			myStack2.reset();
		}
		if(!different)
			System.out.println("The two functions are equivalent!");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int choice = 0;

		System.out.println("Press 1 to generate table, 2 to check if two functions are equivalent.");
		choice = input.nextInt();

		GenTable table = new GenTable();
		if(choice == 1)
			table.calculate();
		else
			table.checkFunction();
	}



}
