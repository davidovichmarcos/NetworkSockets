package com.networks.client;
// A Java program for a Client
import javafx.scene.input.InputMethodTextRun;
import jdk.internal.util.xml.impl.Input;

import java.net.*;
import java.io.*;
import java.util.Scanner;

// es un copypaste de client2
public class Client3 {
    public static String clientAddress;
    public static Integer clientPort;

    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream in = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    // constructor to put ip address and port
    public Client3(String address, int port) {

        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // receives data from server
            in = new DataInputStream(socket.getInputStream());

            // takes input from terminal
            input = new DataInputStream(System.in);
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                System.out.println("[From Server] - " + in.readUTF());

            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {

        Client3 client = new Client3(ClientHelper.getClientAddress(), ClientHelper.getClientPort());
    }

}


