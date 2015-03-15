import java.util.Scanner;

public class IPAddress {
	private static final String MESSAGE_IP_ADDRESS = "%1$s.%2$s.%3$s.%4$s";

	private static String[] binaryArray = new String[4];
	private static int[] ipAddress = new int[4];

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String binaryString = scn.nextLine();
		String formattedIpAddress = null;

		binaryStringToIpAddress(binaryString);

		formattedIpAddress = String.format(MESSAGE_IP_ADDRESS, ipAddress[0],
				ipAddress[1], ipAddress[2], ipAddress[3]);
		
		System.out.println(formattedIpAddress);
	}

	private static void binaryStringToIpAddress(String binaryString) {
		// extracting 8-bit binary sequence for each IP address component
		binaryArray[0] = binaryString.substring(0, 8);
		binaryArray[1] = binaryString.substring(8, 16);
		binaryArray[2] = binaryString.substring(16, 24);
		binaryArray[3] = binaryString.substring(24, 32);

		// converting binary sequence to decimal format for each IP address
		// component
		ipAddress[0] = convertBinaryToDecimal(binaryArray[0]);
		ipAddress[1] = convertBinaryToDecimal(binaryArray[1]);
		ipAddress[2] = convertBinaryToDecimal(binaryArray[2]);
		ipAddress[3] = convertBinaryToDecimal(binaryArray[3]);
	}

	private static int convertBinaryToDecimal(String binaryString) {
		char[] charArray = binaryString.toCharArray();
		int index = 7;
		int decimal = 0;

		for (char digit : charArray) {
			if (digit == '0') {
				decimal += 0 * Math.pow(2, index);
			}
			if (digit == '1') {
				decimal += 1 * Math.pow(2, index);
			}
			index--;
		}

		return decimal;
	}
}
