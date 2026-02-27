import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Server implements Runnable {
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean estab;
    private ExecutorService pool;
    private static int port; //server port #

    public Server() {
        connections = new ArrayList<>();
        estab = false;
    }

    @Override
    public void run() {
        try {
            server= new ServerSocket(port); 
            pool= Executors.newCachedThreadPool();
            System.out.println("Server " + port + " has been booted up!");

            while(!estab) {
                Socket client= server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } 
        catch (IOException e) {
            shutdown();
        }
    }

    public void broadcast(String message) { //messaging in server
        for(ConnectionHandler conn : connections) {
            if (conn!= null) {
                conn.sendmsg(message);
            }
        }
    }

    public void shutdown() {
        try {
            estab= true;
            pool.shutdown();

            if(!server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } 
        catch (IOException e) {
        }
    }

    class ConnectionHandler implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ConnectionHandler(Socket client) {
            this.client= client;
        }

        @Override
        public void run() {
            try {
                out= new PrintWriter(client.getOutputStream(), true);
                in= new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Enter the username you'd like to go by: ");
                username = in.readLine();

                while(username.isBlank()) { //checks for blank usernames
                    out.println("USERNAME CANNOT BE BLANK! Please enter a new username!");
                    username = in.readLine();
                }
                System.out.println(username + " has connected!");
                out.println("You are now connected!");
                broadcast("Server: Welcome " + username + "!");
                String msg;

                while((msg= in.readLine())!= null) { //allows user to disconnect
                    if(msg.startsWith("Bye")) { //leave command
                        System.out.println(username + " has left the chat room.");
                        broadcast("Server: " + username + " has left the chat. Goodbye " + username + "!");
                        shutdown();
                    } 
                    else if(msg.startsWith("/allusers")) { //allows user to check lobby list
                        int counter = 1; //counts command user
                        out.println("Active Users:");

                        for(ConnectionHandler conn : connections) {
                            out.println("");
                            out.println(counter + "> " + conn.username);
                            counter = counter + 1;
                        }
                    }
                    else if(msg.startsWith("/commands")) { //allows user to check commands
                        out.println("/allusers   -displays all users");
                        out.println("Bye   -disconnect from server");
                    } 
                    else { //sends msg
                        broadcast(username + ": " + msg);
                    }
                }
            } 
            catch (IOException e){
                shutdown();
            }

        }

        public void sendmsg(String msg) {
            out.println(msg);
        }

        public void shutdown() {
            try{
                in.close();
                out.close();

                if (!client.isClosed()) {
                    client.close();
                }
            } 
            catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) { //runs server
        Server server= new Server();
        port= Integer.parseInt(args[0]);
        server.run();
    }
}