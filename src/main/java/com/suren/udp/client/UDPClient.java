package com.suren.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	private DatagramSocket socket;
	private InetAddress address;

	private byte[] buf; 
	
	private static int PORT = 4445;

	public UDPClient() {
        try {
			socket = new DatagramSocket();
	        address = InetAddress.getByName("localhost");

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public String sendMessage(String msg) {
		buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
		try {
			socket.send(packet);
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
		} catch (IOException e) {
		
			System.out.println("Exception occurred while sending messages.");
		}
		
		String received = new String(packet.getData(), 0, packet.getLength());
		return received;
	}

	public void close() {
		socket.close();
	}
}
