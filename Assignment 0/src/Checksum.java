import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.CRC32;

public class Checksum {
	private static final String MESSAGE_INCORRECT_ARGUMENTS = "Error: The number of arguments provided is incorrect.";
	private static final String MESSAGE_FILE_NON_EXISTENT = "Error: File does not exist.";

	private static File inputFile = null;

	public static void main(String[] args) {
		// Check for correct number of arguments
		if (args.length != 1) {
			System.out.println(MESSAGE_INCORRECT_ARGUMENTS);
			System.exit(0);
		}

		String inputFileName = args[0];

		// Check if file exists
		if (!new File(inputFileName).exists()) {
			System.out.println(MESSAGE_FILE_NON_EXISTENT);
			System.exit(0);
		}
		
		inputFile = new File(inputFileName);

		processCheckSum(inputFileName);

	}

	private static void processCheckSum(String inputFileName) {
		FileInputStream reader = null;
		
		try {
			int numOfBytes = (int) inputFile.length();
			byte[] buffer = new byte[numOfBytes];
			reader = new FileInputStream(inputFileName);

			reader.read(buffer);
			CRC32 checksum = new CRC32();
			checksum.update(buffer);
			System.out.println(checksum.getValue());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
