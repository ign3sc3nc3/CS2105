// Author: Jonathan Lim Siu Chi
// Matric Number : A0110839H

import java.net.*;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

class FileSender {

	private DatagramSocket senderSocket;
	private File fileToSend;
	private int portNumber;
	private String receiverFileName;

	private static final int MAX_PACKET_SIZE = 1000;

	public static void main(String[] args) {

		// check if the number of command line argument is 4
		if (args.length != 3) {
			System.out.println("Usage: java FileSender <path/filename> "
					+ "<UnreliPort> <rcvFileName>");
			System.exit(1);
		}

		FileSender sender = new FileSender(args[0], args[1], args[2]);
		sender.sendFile();

	}

	public FileSender(String file, String portString, String rcvFileName) {
		this.fileToSend = new File(file);
		this.portNumber = Integer.parseInt(portString);
		this.receiverFileName = rcvFileName;

	}

	public void sendFile() {
		try {
			InetAddress receiverAddress = InetAddress.getByName("localHost");

			// create client socket
			senderSocket = new DatagramSocket();

			// create fileName datagram packet to send to receiver
			byte[] fileName = receiverFileName.getBytes();
			DatagramPacket fileNamePacket = new DatagramPacket(fileName,
					fileName.length, receiverAddress, portNumber);

			// send fileName datagram packet
			senderSocket.send(fileNamePacket);
			Thread.sleep(1);

			// send fileSize datagram packet
			byte[] fileSizeBuffer = ("" + fileToSend.length()).getBytes();
			DatagramPacket fileSizePacket = new DatagramPacket(fileSizeBuffer,
					fileSizeBuffer.length, receiverAddress, portNumber);
			senderSocket.send(fileSizePacket);
			Thread.sleep(1);

			// sending file in packets of at most 1000 bytes
			FileInputStream input = new FileInputStream(fileToSend);
			byte[] fileBuffer = new byte[MAX_PACKET_SIZE];
			int numOfBytes = 0;

			while ((numOfBytes = input.read(fileBuffer)) != -1) {
				DatagramPacket packet = new DatagramPacket(fileBuffer,
						numOfBytes, receiverAddress, portNumber);
				senderSocket.send(packet);
				Thread.sleep(1);
			}

			int fileSizeInBytes = (int) fileToSend.length();
			System.out
					.println("File of " + fileSizeInBytes + " bytes is sent.");
			input.close();
			senderSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Input/Output exception encountered.");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Interrupted exception encountered.");
		}
	}
}