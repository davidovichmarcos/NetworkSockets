package com.networks.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class Multi extends Thread{
    private Socket s=null;
    DataInputStream infromClient;
    Multi() throws IOException{


    }
    Multi(Socket s) throws IOException{
        this.s=s;
        infromClient = new DataInputStream(s.getInputStream());
    }
    public void run(){

        String SQL=new String();
        try {
            SQL = infromClient.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Query: " + SQL);
        try {
            System.out.println("Socket Closing");
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public class Server {

    public static void main(String args[]) throws IOException,
            InterruptedException{

        while(true){
            ServerSocket ss=new ServerSocket(3000);
            System.out.println("Server is Awaiting");
            Socket s=ss.accept();
            Multi t=new Multi(s);
            t.start();

            Thread.sleep(2000);
            ss.close();
        }




    }
}