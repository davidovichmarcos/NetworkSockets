package com.networks.server;// Chat Server runs at port no. 9020
import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;

public class Server
{
    Vector<String> users = new Vector<>();
    Vector<HandleClient> clients = new Vector<HandleClient>();

    int PORT = 3000;
    int NumClients = 10;

    public static void main(String ... args) throws Exception
    {
        new Server().process();
    }


    public void process() throws Exception
    {
        ServerSocket server = new ServerSocket(PORT,NumClients);
        out.println("Server Connected...");
        // waitCconnections escucha constantemente nuevos clientes
        waitConnections wc = new waitConnections(server);
        // ServerChatter habilita mensajes desde el servidor un cliente especifico o a todos.
        new ServerChatter().start();

    }


    public void broadcast(String user, String message)
    {
        for (HandleClient c : clients)
                c.sendMessage(user,message);
    }

    public void serverBroadcast(String user, String message)
    {
        if(user.equals("*")){
            //manda a todos
            broadcast("SERVER", message);
        } else {
            if (users.contains(user)) {

                for (HandleClient c : clients) {
                    if (c.getUserName().equals(user)) {
                        c.sendMessage(" PRIVATE FROM SERVER ",message);
                    }
                }
                out.println("[MESSAGE SENT]");
            }else {
                out.println("[USER NOT FOUND]");
            }

        }
    }


    class ServerChatter extends Thread
    {
        String message ="";
        String user ="";
        Scanner scanner = new Scanner(System.in);

        @Override
        public void run() {

            // escribir "sm" para abrir consola de mensajes
            while(true) {
                message = scanner.nextLine();
                if(message.equals("sm")) {
                    createMessage();
                }
                message="";
             }
        }

        private void createMessage(){
            System.out.printf(" [ DESTINATION ] (Hint: write * to send to every user) *> ");
            user = scanner.nextLine();
            System.out.printf(" [ MESSAGE ] *> ");
            message = scanner.nextLine();
            serverBroadcast(user, message);
        }
    }

    // Hilo que sigue esperando conecciones
    class waitConnections extends Thread
    {
        ServerSocket server;

        public waitConnections(ServerSocket server){
            this.server = server;
            start();
        }

        @Override
        public void run(){
            while( true)
            {
                Socket client = null;
                try {
                    client = this.server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println("server > [ Cliente nuevo ]");
                HandleClient c = null;
                try {
                    c = new HandleClient(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clients.add(c);
            }
        }
    }


    // Cada cliente que entra es un hilo
    class HandleClient extends Thread
    {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket client) throws Exception
        {
            input = new BufferedReader(new InputStreamReader(client.getInputStream())) ;
            output = new PrintWriter (client.getOutputStream(),true);
            output.println("Welcome to UTN's Chat Server! : Please Enter a User Name ");
            name  = input.readLine();

            out.println("** NOMBRE: " + name);
            users.add(name);
            output.println("Welcome "+name+" we hope you enjoy your chat today");
            start();
        }

        public void sendMessage(String userName,String  msg)
        {
            output.println( "["+userName + "] :" + msg);
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
                    if("x".equals(line))
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
                    broadcast(name,line);
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}