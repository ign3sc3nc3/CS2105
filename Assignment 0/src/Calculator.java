import java.util.Scanner;

public class Calculator {
	private static final String MESSAGE_ERROR = "Error in expression";
	private static final String MESSAGE_SOLUTION = "%1$s %2$s %3$s = %4$s";

	public static void main(String[] args) {
		int firstOperand = 0;
		int secondOperand = 0;
		String operator = null;

		// checks if user input gives 3 arguments correctly
		if (args.length != 3) {
			exitProgramDueToError();
		}

		// check if operands are correct
		try {
			firstOperand = Integer.parseInt(args[0]);
			secondOperand = Integer.parseInt(args[2]);

		} catch (NumberFormatException e) {
			exitProgramDueToError();
		}

		// check for divide-by-0 error
		if (secondOperand == 0) {
			exitProgramDueToError();
		}

		// Operands are correct. Check operator and process answer.
		operator = args[1];
		int answer = calculate(firstOperand, secondOperand, operator);

		// print answer
		printSolution(firstOperand, secondOperand, operator, answer);
	}

	private static int calculate(int firstOperand, int secondOperand,
			String operator) {
		int answer = 0;

		switch (operator) {
		case "+":
			answer = firstOperand + secondOperand;
			break;
		case "-":
			answer = firstOperand - secondOperand;
			break;
		case "*":
			answer = firstOperand * secondOperand;
			break;
		case "/":
			answer = firstOperand / secondOperand;
			break;
		default:
			exitProgramDueToError();
		}

		return answer;
	}

	private static void printSolution(int firstOperand, int secondOperand,
			String operator, int answer) {
		String solution = String.format(MESSAGE_SOLUTION, firstOperand,
				operator, secondOperand, answer);
		System.out.println(solution);
	}

	private static void exitProgramDueToError() {
		System.out.println(MESSAGE_ERROR);
		System.exit(0);
	}
}
