package com.networks.server;// Chat Server runs at port no. 9020
import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;

public class Server
{
    Vector<String> users = new Vector<String>();
    Vector<HandleClient> clients = new Vector<HandleClient>();

    //int PORT = 9020;
    int NumClients = 10;

    public void process() throws Exception
    {
        ServerSocket server = new ServerSocket(3000,NumClients);
        out.println("Server Connected...");
        while( true)
        {
            Socket client = server.accept();
            out.println("server > [ Cliente nuevo ]");
            HandleClient c = new HandleClient(client);
            clients.add(c);
        }  // end of while
    }

    public static void main(String ... args) throws Exception
    {
        new Server().process();
    } // end of main

    public void boradcast(String user, String message)
    {
        // send message to all connected users
        for (HandleClient c : clients)
            if (!c.getUserName().equals(user))
            {
                c.sendMessage(user,message);
            }
    }

    class HandleClient extends Thread
    {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket client) throws Exception
        {
            // get input and output streams
            input = new BufferedReader(new InputStreamReader(client.getInputStream())) ;
            output = new PrintWriter (client.getOutputStream(),true);
            output.println("Welcome to Bob's Chat Server! : Please Enter a User Name ");
            // read name
            name  = input.readLine();

            out.println("** NOMBRE: " + name);
            users.add(name); // add to vector
            output.println("Welcome "+name+" we hope you enjoy your chat today");
            start();
        }

        public void sendMessage(String uname,String  msg)
        {
            output.println( uname + ":" + msg);
        }

        public String getUserName()
        {
            return name;
        }

        public void run()
        {
            String line;
            out.println("-- WAITING");
            try
            {
                while(true)
                {
                    line = input.readLine();
                    out.println("server > [ "+name+" ]: " +line);
                    if("EXIT".equals(line))
                    {
                        output.println("Closing Connection  . . . Goodbye");
                        clients.remove(this);
                        users.remove(name);
                        break;
                    }
                    else if(name.equals(line))
                    {
                        output.println("OK");
                    }
                    boradcast(name,line); // method  of outer class - send messages to all
                }// end of while
            } // try
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        } // end of run()
    } // end of inner class
} // end of Server