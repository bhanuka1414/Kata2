package uk.ac.wlv.cs5006.kata2;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata2 {

	private static boolean isAlphabets(String str) {
		Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static double evaluate(String expression) {

		if (expression == null) {
			return 0;
		} else if (expression.isEmpty()) {
			return 0;
		} else if (isAlphabets(expression)) {
			return 0;
		} else {
			char[] tokens = expression.toCharArray();

			// Stack for numbers: 'values'
			Stack<Double> values = new Stack<>();

			// Stack for Operators: 'ops'
			Stack<Character> ops = new Stack<>();

			for (int i = 0; i < tokens.length; i++) {
				// Current token is a whitespace, skip it
				if (tokens[i] == ' ')
					continue;

				// Current token is a number, push it to stack for numbers
				if (tokens[i] >= '0' && tokens[i] <= '9') {
					StringBuffer sbuf = new StringBuffer();
					// There may be more than one digits in number
					while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
						sbuf.append(tokens[i++]);
					values.push(Double.parseDouble(sbuf.toString()));
				}

				// Current token is an operator.
				else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
					// token, which is an operator. Apply operator on top of 'ops'
					// to top two elements in values stack
					while (!ops.empty()) {
						try {
							values.push(applyOp(ops.pop(), values.pop(), values.pop()));
						} catch (Exception e) {
							// in here return zero when divide by zero or string not wel formated
							return 0;
						}
					}

					// Push current token to 'ops'.
					ops.push(tokens[i]);
				}
			}

			// Entire expression has been parsed at this point, apply remaining
			// ops to remaining values
			while (!ops.empty()) {
				try {
					values.push(applyOp(ops.pop(), values.pop(), values.pop()));
				} catch (Exception e) {
					// in here return zero when divide by zero or string not wel formated
					return 0;
				}
			}

			// Top of 'values' contains result, return it
			return values.pop();
		}

	}

	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	private static double applyOp(char op, Double double1, Double double2) {
		switch (op) {
		case '+':
			return double2 + double1;
		case '-':
			return double2 - double1;
		case '*':
			return double2 * double1;
		case '/':
			if (double1 == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return double2 / double1;
		}
		return 0;
	}

}
