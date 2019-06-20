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
    private String host = ClientHelper.getClientAddress();
    private Integer port = ClientHelper.getClientPort();
    public TelnetClient() {
        this.host = host;
    }

    public void getData(){
        Socket s = new Socket();
        PrintWriter s_out = null;
        BufferedReader s_in = null;

        try {
            s.connect(new InetSocketAddress(host , port));
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

        //listener se mantiene constantemente escuchando mensajes del servidor
        // y los imprime a la consola
        Listener l = new Listener(s_in);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pick a nickname: ");
        //String response;
        String message ="";

        while(!message.equals("abort")) {
            //Send message to server
            System.out.printf(" * > ");
            message = scanner.nextLine();
            s_out.println( message );
        }
    }


     public class Listener extends Thread {
         BufferedReader s_in = null;

         public Listener(BufferedReader s_in){
             this.s_in = s_in;
            start();
        }
        public void run(){
             String response;
            while(true){
                try {
                    if ((response = s_in.readLine()) != null) {
                        System.out.println( response );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
     }

    public static void main(String args[])
    {

        TelnetClient telnetClient = new TelnetClient();
        telnetClient.getData();
    }

}
