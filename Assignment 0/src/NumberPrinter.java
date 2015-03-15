import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NumberPrinter extends TimerTask {
	private static final String MESSAGE_INCORRECT_ARGUMENTS = "Error: The number of arguments provided is incorrect.";

	private static String stringToPrint = null;

	public NumberPrinter(String printString) {
		stringToPrint = printString;
	}

	public void run() {
		System.out.println(stringToPrint);
	}

	public static void main(String[] args) {
		// Check the number of arguments
		if (args.length != 3) {
			System.out.println(MESSAGE_INCORRECT_ARGUMENTS);
			System.exit(0);
		}

		String toPrint = args[0];
		long startTime = Long.parseLong(args[1]) * 1000;
		long interval = Long.parseLong(args[2]) * 1000;

		Timer timer = new Timer();
		timer.schedule(new NumberPrinter(toPrint), startTime, interval);

		// Exit if user inputs 'q'
		Scanner userInput = new Scanner(System.in);
		if (userInput.next().equals("q")) {
			timer.cancel();
			System.exit(0);
		}

	}

}
