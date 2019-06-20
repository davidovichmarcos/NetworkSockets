package com.networks.client;
// A Java program for a Client
import javafx.scene.input.InputMethodTextRun;
import jdk.internal.util.xml.impl.Input;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import static com.networks.client.Client.clientAddress;


public class Client2
{

    private static int clientPort;
    // initialize socket and input output streams

    private DataInputStream input = null;
    private DataOutputStream out	 = null;

    // constructor to put ip address and port
    public Client2(String address, int port) throws IOException {

        try {

        }catch (Exception e){
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(address,port));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Client says:");
            socket.close();
        }
    }

    public static void main(String args[]) throws IOException {

        Client2 client = new Client2(ClientHelper.getClientAddress(), ClientHelper.getClientPort());
    }

}


