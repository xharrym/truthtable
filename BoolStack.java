/*
 * xharrym
 * 2014
 *
 * Manage the stack to evaluate the function
 *
 */
public class BoolStack {
	public boolean[] stack;
	public int pointer;

	public BoolStack(){
		stack = new boolean[100];
		pointer = 0;
	}

	public void reset(){
		pointer = 0;
	}

	public void push(boolean x){
		stack[pointer] = x;
		pointer++;

		if(pointer == 100)
			System.out.println("Stack is full!");
	}

	public void and(){
		pointer = pointer-1;
		stack[pointer-1] = stack[pointer] & stack[pointer-1];
	}

	public void or(){
		pointer = pointer-1;
		stack[pointer-1] = stack[pointer] | stack[pointer-1];
	}

	public void not(){
		stack[pointer-1] = !stack[pointer-1];
	}

	public boolean returnResult(){
		return stack[0];
	}

}
