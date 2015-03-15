import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class Copier {
	private static final String MESSAGE_INCORRECT_ARGUMENTS = "Error: Number of arguments entered is incorrect.";
	private static final String MESSAGE_NON_EXISTENT_FILE = "Error: File to be copied does not exist.";
	private static final String MESSAGE_SUCCESSFUL_COPY = "%1$s is successfully copied to %2$s";

	private static File inputFile;
	private static File outputFile;

	public static void main(String[] args) {
		// Checks the number of arguments
		if (args.length != 2) {
			System.out.println(MESSAGE_INCORRECT_ARGUMENTS);
			System.exit(0);
		}

		// Number of arguments are correct.
		String inputFileName = args[0];
		String outputFileName = args[1];

		// Check if input file exists
		if (!new File(inputFileName).exists()) {
			System.out.println(MESSAGE_NON_EXISTENT_FILE);
			System.exit(0);
		}

		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);

		copyFile();

		printSuccessfulMessage();
	}

	private static void copyFile() {
		BufferedInputStream reader = null;
		BufferedOutputStream writer = null;

		try {
			reader = new BufferedInputStream(new FileInputStream(inputFile));
			writer = new BufferedOutputStream(new FileOutputStream(outputFile));

			byte[] buffer = new byte[4096];
			int bytesInBuffer;

			// reads bytes into buffer and writes to file from buffer until End
			// Of File
			while ((bytesInBuffer = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytesInBuffer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void printSuccessfulMessage() {
		String message = String.format(MESSAGE_SUCCESSFUL_COPY,
				inputFile.getName(), outputFile.getName());
		System.out.println(message);
	}
}
