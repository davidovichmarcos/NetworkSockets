package com.networks.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		try {
			new Server().process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
