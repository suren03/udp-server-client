package com.suren.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread {

	private DatagramSocket socket;
	private boolean running;
	private byte[] buf = new byte[256];
	private static final int PORT = 4445;

	public UDPServer() throws SocketException {
		socket = new DatagramSocket(PORT);
	}

	public void run() {
		running = true;

		while (running) {
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				System.out.println("Exception occurred while receiving the packets.");
				e.printStackTrace();
			}

			String received = new String(packet.getData(), 0, packet.getLength());
			System.out.println("UDP Server - Received Message is - " + received);

			// We can respond to the client by getting the address and port from the client.
			/*
			 * InetAddress address = packet.getAddress(); int port = packet.getPort();
			 * packet = new DatagramPacket(buf, buf.length, address, port); String received
			 * = new String(packet.getData(), 0, packet.getLength());
			 * 
			 * try { socket.send(packet); } catch (IOException e) {
			 * System.out.println("Received end  string, so stopping the server.");
			 * e.printStackTrace(); }
			 */

			// Just to stop the code
			if (received.equals("end")) {
				running = false;
				continue;
			}

		}
		socket.close();
	}

}
