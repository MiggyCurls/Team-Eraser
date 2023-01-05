package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {

	/**
	*  Determines if the first operator has same or greater
    *  precedence than the second
	*
	* @param op1 the first operator
	* @param op2 the second operator
	* @return the boolean result
	*/
	private static boolean hasPrecedence(String op1, String op2) {
		return (opToPrcd(op1) > opToPrcd(op2));
	}


	/**
	* Converts an operator to its precedence priority
	*
	* We expect you to be able to handle +, -, *, /, ^, and (
	* (why don't you need ")" as well? see algorithm in part 4)
	*
	* The order of these is as follows:
	*    ^, * and /, + and -, (
	*
	* @param op a string representing an operator, e.g. "+" or "-"
	* @return an integer value reflecting its precedence
	*/
	private static int opToPrcd(String op) {
		if(op.equals("^")) {
			return 5;
		}else if(op.equals("*") || op.equals("/")) {
			return 4;
		}else if(op.equals("+") || op.equals("-")) {
			return 3;
		}
	    return 0; // placeholder
	}

	/**
	* determines if the input token is an operator
	*
	* @param token the string token to check
	* @return a boolean reflecting the result
	*/
	private static boolean isOperator(String token) {
		if(token.equals("^") || token.equals("*") || token.equals("/")
				|| token.equals("+") || token.equals("-")) {
			return true;
		}
		return false; // placeholder
	}

	/**
    * Evaluates an expression
    *
    * NOTE Beware the order of pop and evaluation when receiving operand1
    * and operand2 as input.
    *
    * @param operand1 the first operand
    * @param operator the operator to apply
    * @param operand2 the second operand
    * @return a double expressing the result
    * @throws IllegalArgumentException if operator passed is not one of
    *  "+", "-", "*", "/", or "^"
    *
	*/
	private static double evaluate(double operand1, String operator, double operand2){
		try {
			if(!isOperator(operator)) {
				throw new IllegalArgumentException();
			}
			if(operator.equals("^")) {
				return (Math.pow(operand1, operand2));
			}else if(operator.equals("*")) {
				return (operand1 * operand2);
			}else if(operator.equals("/")) {
				return (operand1 / operand2);
			}else if(operator.equals("+")) {
				return (operand1 + operand2);
			}else if(operator.equals("-")){
				return (operand1 - operand2);
			}else {
				return 0.0;
			}
		}catch(IllegalArgumentException e) {
			System.out.println("operator is not a recognized operator");
		}
		return 0.0; // placeholder
	}


	/**
	* Evaluates an expression in infix notation via Reverse Polish Notation
	* @param String of the infix expression
	* @return Double of the evaluated expression
	*/
	public static double infixEvaluator(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();
		while(data.hasNext()) {
			if(!isOperator(data.peek()) && !(data.peek().equals("(") || data.peek().equals(")"))) {
				operands.push(Double.valueOf(data.next()));
			}else if(data.peek().equals("(")) {
				operators.push(data.next());
			}else if(data.peek().equals(")")) {
				while(!operators.peek().equals("(")) {
					String s = operators.pop();
					Double num1 = operands.pop();
					Double num2 = operands.pop();
					operands.push(evaluate(num2,s,num1));
				}
				operators.pop();
				data.next();
			}else if(isOperator(data.peek())) {
				while(!operators.empty() && (opToPrcd(operators.peek()) >= opToPrcd(data.peek()))) {
					String s = operators.pop();
					Double num1 = operands.pop();
					Double num2 = operands.pop();
					operands.push(evaluate(num2,s,num1));
				}
				operators.push(data.next());
			}
		}
		
		while(!operators.empty()) {
			String s = operators.pop();
			Double num1 = operands.pop();
			Double num2 = operands.pop();
			operands.push(evaluate(num2,s,num1));
		}
		Double answer = operands.pop();
		System.out.println("answer: " + answer);
		return answer; // placeholder
	}

	/**
	* Converts an infix notation expression to a post fix notation
	* @param String of the infix notation expression
	* @return String of the post fix notation expression
	*/
	public static String toPostfix(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		String postFix = new String();
		while(data.hasNext()) {
			if(!isOperator(data.peek()) && !(data.peek().equals("(") || data.peek().equals(")"))){
				postFix += data.next();
			}else if(isOperator(data.peek())) {
				if(!operators.empty()) {
					while(!operators.peek().equals("(") && hasPrecedence(operators.peek(),data.peek())) {
						postFix += operators.pop();
					}
					operators.push(data.next());
				}
			}else if(data.peek().equals("(")) {
				operators.push(data.next());
			}else if(data.peek().equals(")")) {
				while(!operators.peek().equals("(")) {
					postFix += operators.pop();
				}
				operators.pop();
				data.next();
			}
		}
		System.out.println("postFix: " + postFix);
		return postFix; // placeholder
	}


	public static void main(String[] args){
		
        if (infixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test1 failed --> your answer should have been 12");

        if (infixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");

        if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");

        if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");


        System.out.println("Lab Testing Done!!!");

        /* uncomment the below lines for assignmemt */
		 if (!toPostfix(new String("(4+5)")).equals("45+"))
		     System.err.println("test1 failed --> should have been 45+");

		 if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		     System.err.println("test2 failed --> should have been 45+6*");

		 if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
		     System.err.println("test3 failed --> should have been 456*7/+8-");

		 if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		     System.err.println("test4 failed --> should have been 4567-*+8/");

		 if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
		     System.err.println("test5 failed --> should have been 987*654^/3*-2*+");


         System.out.println("Assignment Testing Done!!!");


	}

}
