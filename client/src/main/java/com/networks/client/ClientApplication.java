package com.networks.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		try {
			TelnetClient.main(args);
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

}
