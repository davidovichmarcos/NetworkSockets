package com.networks.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class Multi extends Thread{

    private Socket s=null;
    DataInputStream in;
    DataOutputStream out;
    String client;

    //este lo borramos?
    Multi() throws IOException{
    }

    Multi(Socket s){
        this.s=s;
        this.client = s.getInetAddress().getHostAddress();
        System.out.println(this.client +" has joined the server.");
    }


    public void run(){

        String txt="";

        try {
            in = new DataInputStream(s.getInputStream());

            while (true){
                if(!txt.equals("Over")) {

                    txt = in.readUTF();

                    // si el mensaje no es vacio lo imprime y responde
                    if(!txt.equals(" ")){
                        System.out.println("Message from ["+ this.client + " ]: " + txt);

                        // para escribir respuesta aca habria que agregar
                        // un system input....por ahora, envia una respuesta generica...
                        out = new DataOutputStream(s.getOutputStream());
                        out.writeUTF("Message Received");
                    }

                } else {
                    break;
                }
            }

            System.out.println("Client is out, Socket Closing");
            s.close();

        } catch (IOException ex) {
            Logger.getLogger(Multi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}


public class Server {

    public static void main(String args[]) throws IOException,
            InterruptedException{

            ServerSocket ss=new ServerSocket(3000);
            System.out.println("Server is Awaiting");

        while(true){
            Socket s=ss.accept();
            Multi t=new Multi(s);
            t.start();
        }

    }
}