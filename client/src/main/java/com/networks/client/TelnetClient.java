package com.networks.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TelnetClient {
    private String host;

    public TelnetClient(String host) {
        this.host = host;
    }

    public void getData(){
        Socket s = new Socket();
        PrintWriter s_out = null;
        BufferedReader s_in = null;

        try {
            s.connect(new InetSocketAddress("127.0.0.1" , 3000));
            System.out.println("Connected");

            //writer for socket
            s_out = new PrintWriter( s.getOutputStream(), true);
            //reader for socket
            s_in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        }

        //Host not found
        catch (UnknownHostException e) {
            System.err.println("Don't know about host : " + host);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pick a nickname: ");
        //Send message to server
        String message = scanner.nextLine();

        s_out.println( message );

        System.out.println("Message send");

        //Get response from server
        String response;
        try {
            while ((response = s_in.readLine()) != null) {
                System.out.println( response );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {

        TelnetClient telnetClient = new TelnetClient("127.0.0.1");
        telnetClient.getData();
    }

}
