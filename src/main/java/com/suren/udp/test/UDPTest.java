package com.suren.udp.test;

import java.net.SocketException;

import com.suren.udp.client.UDPClient;
import com.suren.udp.server.UDPServer;

public class UDPTest {

	public static void main(String[] args) {

		// Start the UDP Server -
		try {
			UDPServer server = new UDPServer();
			server.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}

		// Send messages to the client
		UDPClient client = new UDPClient();
		System.out.println(client.sendMessage("Hi, This is Surendra Kumar. Testing UDP protocol.!!!"));
		client.sendMessage("end");
		client.close(); 
		
	}
}
