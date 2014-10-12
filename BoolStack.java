import java.util.Stack;

/**
*	Manage the stack to evaluate the function.
*
*	@author xharrym 2014
*	@author Michael Mckee October 2014
*/

public class BoolStack {
	private Stack<Boolean> stack;

	public BoolStack(){
		this.stack = new Stack<Boolean>();
	}

  /**
   * Empties the stack.
   */
	public void reset(){
		while(!stack.empty()){
			stack.pop();
		}
	}

  /**
   * Push a boolean onto the stack.
   *
   * @param x The boolean to be pushed.
   */
  public void push(boolean x){
    stack.push(x);
  }

  /**
   * Push the result of AND for the top two elements of the stack.
   */
  public void and(){
    stack.push(stack.pop() & stack.pop());
  }

  /**
   * Push the result of OR for the top two elements of the stack.
   */
  public void or(){
    stack.push(stack.pop() | stack.pop());
  }

  /**
   * Push the NOT of the top element of the stack.
   */
  public void not(){
    stack.push(!stack.pop());
  }

  public boolean returnResult(){
    return stack.pop();
  }
}
