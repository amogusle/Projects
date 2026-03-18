import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Random;

public class Dealer implements Runnable {
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean estab;
    private ExecutorService pool;
    private static int port; //server port #
    private static int players= 0; //player count
    private static boolean start;
    private static Player[] turn= new Player[4]; //holds player names to determine turn order

    public Dealer() {
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
                broadcast("Dealer: Welcome " + username + "!");
                String msg;
                
                while(!start) { //while game not started
                    while((msg= in.readLine())!= null) {
                        if (msg.startsWith("/start")) { //start game
                            broadcast(username + ": " + msg);
                            System.out.println(username + " has started a game.");
                            broadcast(username + " has started a game.");
                            
                            start= true;
                        }
                        else if(msg.startsWith("/leave")) { //leave command
                            System.out.println(username + " has left the table.");
                            broadcast("Dealer: " + username + " has left the table. Goodbye " + username + "!");
                            shutdown();
                        } 
                        else if(msg.startsWith("/players")) { //allows user to check lobby list
                            int counter = 1; //counts command user
                            out.println("-------------------------\nCurrent Players:");

                            for(ConnectionHandler conn : connections) {
                                out.println("");
                                out.println(counter + "> " + conn.username);
                                counter = counter + 1;
                            }
                            out.println("-------------------------");
                        }
                        else if(msg.startsWith("/commands")) { //allows user to check commands
                            broadcast("-------------------------\nCommand List:\n");
                            out.println("/players   -displays all players\n/leave   -leave table\n/start   -start game\nHit   -gain a card\nStand   -end turn");
                            broadcast("-------------------------\n");
                        } 
                        else { //sends msg
                            broadcast(username + ": " + msg);
                        }
                    }
                }
                while(start) { //when game starts
                    int play= 0; //turn order
                    String[] deck= shuffle();
                    int cards= 51;
                    
                    for(ConnectionHandler conn : connections) { 
                        if(play== 0) { //set dealer
                            turn[play]= new Player(conn.username);
                            turn[play].becomedealer();
                            turn[play].add(deck[51]);
                            cards= cards - 1;
                            play= 4;
                        } 
                        else if(turn[play]== null) {    //if missing players
                            break;
                        }
                        turn[play]= new Player(conn.username);
                        play= play - 1;
                        cards= cards - 1;
                    }

                    play= 4;
                    
                    broadcast("-------------------------\n");
                    broadcast("Dealer " + turn[0].getname() + " is showing " + turn[0].dealershows());

                    for(int i=1; i!= 4; i++ ) {
                        if(turn[i]== null) {
                            break;
                        }
                        broadcast(turn[i].getname() + "'s Hand: " + turn[i].gethand() + "\nTotal: " + turn[i].gettotal() + "\n");
                    }
                    broadcast("-------------------------\n");

                    while((msg= in.readLine())!= null) {
                        if(turn[play]== null) {
                            play= 0; //skip to dealer if theres a null
                        }
                        if(msg.startsWith("Hit")) {
                            if(turn[play].getname()== username) {
                                turn[play].add("1");
                                if(turn[play].gettotal()> 21) {
                                    broadcast(turn[play].getname() + " has just busted!");
                                    play= play - 1; //go next player
                                }
                            }
                            else {
                                out.println("It is not yet your turn to go!");
                            }
                        }
                        if(msg.startsWith("Stand")) {
                            if(turn[play].getname()== username) { //if it is players turn
                                if(play== 0) { //rule against dealer
                                    if(turn[play].gettotal()<= 16) {
                                        out.println("As the dealer, you cannot stand on any total at 16 or below!");
                                    }
                                    else {
                                        break;
                                    }
                                }
                                play= play - 1; //go to next player turn
                            }
                            else { //if it is not players turn
                                out.println("It is not yet your turn to go!");
                            }
                        }
                    }
                    
                    for(int i= 5; i >= 1; i--) { //announce results
                        if(turn[play]== null) {
                            break;
                        }
                        if(turn[play].gettotal()> turn[0].gettotal() && turn[play].gettotal()<= 21) { //compare against dealer
                            broadcast(turn[play].getname() + " has beaten the dealer!");
                        }
                        else if(turn[play].gettotal()== turn[0].gettotal() && turn[play].gettotal()<= 21) {
                            broadcast(turn[play].getname() + " has pushed with the dealer!");
                        }
                        else if (turn[play].gettotal() > 21) {
                            broadcast(turn[play].getname() + "had went over and busted!");
                        }
                        else {
                            broadcast(turn[play].getname() + "got less than and lost to the dealer!");
                        }
                    }
                    start= false;
                }
            }
            catch (IOException e){
                shutdown();
            }

        }

        public String[] shuffle() {
            String[] deck= {"A", "A", "A", "A", "2", "2", "2", "2", "3", "3", "3", "3", "4", "4", "4", "4", "5", "5", "5", "5",
            "6", "6", "6", "6", "7", "7", "7", "7", "8", "8", "8", "8", "9", "9", "9", "9", "10", "10", "10", "10",
            "J", "J", "J", "J", "Q", "Q", "Q", "Q", "K", "K", "K", "K"};
            Random rand = new Random();

            for (int i = deck.length - 1; i > 0; i--) { //shuffle
                int j = rand.nextInt(i + 1);
                String temp = deck[i];
                deck[i] = deck[j];
                deck[j] = temp;
            }
            return deck;
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
        Dealer table= new Dealer();
        port= Integer.parseInt(args[0]);
        table.run();
    }
}