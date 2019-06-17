package com.networks.client;
// A Java program for a Client
import javafx.scene.input.InputMethodTextRun;
import jdk.internal.util.xml.impl.Input;

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Client2
{
    public static String clientAddress;
    public static Integer clientPort;
    // initialize socket and input output streams
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;

    // constructor to put ip address and port
    public Client2(String address, int port)
    {


        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            // takes input from terminal
            input = new DataInputStream(System.in);
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {

        Client2 client = new Client2(getClientAddress(), getClientPort());
    }

    public static String getClientAddress() {

        System.out.println("insert address");
        Scanner scanner = new Scanner( System. in);
        clientAddress = scanner. nextLine();
    return clientAddress;
    }
    public static Integer getClientPort() {
        Scanner scanner = new Scanner( System. in);
        System.out.println("insert port")   ;
        clientPort = scanner.nextInt();
        return clientPort;
    }
}


