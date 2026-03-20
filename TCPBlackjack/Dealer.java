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
    private static boolean start;
    private static boolean mid= false;
    private static boolean end= false;
    private static Player[] turn= new Player[5]; //holds player names to determine turn order
    private static int play= 0; //turn order
    private String[] deck;
    private static int cards= 0;

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
                broadcast("Server: Welcome " + username + "!");
                String msg;
                start= false;

                 //while game not started
                while((msg= in.readLine())!= null) {
                    if(start== false) {
                        if (msg.startsWith("/start")) { //start game
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
                            out.println("-------------------------\nCommand List:\n");
                            out.println("/players   -displays all players\n/leave   -leave table\n/start   -start game\nHit   -gain a card\nStand   -end turn");
                            out.println("-------------------------\n");
                        } 
                        else { //sends msg
                            broadcast(username + ": " + msg);
                        }
                    }
                    
                    if(start== true) { //when game starts
                        broadcast("-------------------------\nServer: Game is loading...");
                        deck= shuffle();
                            
                        for(ConnectionHandler conn : connections) { //set players
                            if(play== 4) {
                                turn[play]= new Player(conn.username);
                                turn[play].add(deck[cards]);
                                turn[play].add(deck[cards + 1]);
                                play= play - 1;
                                cards= cards + 2;
                            }
                            else if(play== 3) {
                                turn[play]= new Player(conn.username);
                                turn[play].add(deck[cards]);
                                turn[play].add(deck[cards + 1]);
                                play= play - 1;
                                cards= cards + 2;
                            }
                            else if(play== 2) {
                                turn[play]= new Player(conn.username);
                                turn[play].add(deck[cards]);
                                turn[play].add(deck[cards + 1]);
                                play= play - 1;
                                cards= cards + 2;
                            }
                            else if(play== 1) {
                                turn[play]= new Player(conn.username);
                                turn[play].add(deck[cards]);
                                turn[play].add(deck[cards + 1]);
                                play= play - 1;
                                cards= cards + 2;
                                break;
                            }
                            else if(play== 0) { //set dealer
                                turn[play]= new Player(conn.username);
                                turn[play].becomedealer();
                                turn[play].add(deck[cards]);
                                turn[play].add(deck[cards + 1]);
                                cards= cards + 2;
                                play= 4;
                            } 
                            else {
                                broadcast("idk");
                            }
                        }
                        broadcast("Server: Hands are now being dealt!");
                        play= 4;
                        
                        broadcast("-------------------------\n"); //display hands
                        broadcast("Dealer " + turn[0].getname() + " is showing " + turn[0].dealershows() + "\n");

                        for(int i= 4; i!= 0; i--) { 
                            if(turn[i]== null) {
                                break;
                            }
                            broadcast(turn[i].getname() + "'s Hand: " + turn[i].gethand() + "      Total: " + turn[i].gettotal() + "\n");
                        }
                        broadcast("Dealer " +turn[0].getname() + ": It's " + turn[play].getname() + "' turn! Will you hit or stand?");
                        broadcast("-------------------------\n"); //end of hand display
                        start= false;
                        mid= true;
                    } //end of start

                    if(mid== true) { //while game is going
                        if(turn[play]== null) {
                            play= 0; //skip to dealer if theres a null
                        }
                        if(turn[play].gettotal()== 21) { //check for blackjack
                            broadcast(turn[play].getname() + " has got a blackjack!");
                            play= play - 1; //go next player
                        }
                        if(msg.startsWith("Hit")) { //if player hits
                            if(turn[play].getname()== username) {
                                turn[play].add(deck[cards]);
                                broadcast("-------------------------\n" + turn[play].getname() + " has hit and got a " + deck[cards] + "!\n");
                                broadcast("Dealer " + turn[0].getname() + " is showing " + turn[0].dealershows() + "\n");

                                for(int i= 4; i!= 0; i--) { //display hands after hit

                                    if(turn[i]== null) {
                                        break;
                                    }
                                    broadcast(turn[i].getname() + "'s Hand: " + turn[i].gethand() + "      Total: " + turn[i].gettotal() + "\n");
                                }
                                broadcast("Dealer " +turn[0].getname() + ": It's " + turn[play].getname() + "'s turn! Will you hit or stand?");
                                broadcast("-------------------------\n"); //end of hand display
                                cards= cards - 1;

                                if(turn[play].gettotal()> 21) {
                                    broadcast(turn[play].getname() + " has just busted!");
                                    if(turn[play].checkdealer()== true) {
                                        end= true;
                                    }
                                    else {
                                        displayhand();
                                        play= play - 1; //go next player
                                    }
                                }
                            }
                            else {
                                out.println("It is not yet your turn to go!");
                            }
                            
                        } //end of hit cmd
                        else if(msg.startsWith("Stand")) { //if player stands
                            if(turn[play].getname()== username) { //if it is players turn
                                if(play== 0) { //rule against dealer
                                    if(turn[play].gettotal()<= 16) {
                                        out.println("As the dealer, you cannot stand on any total at 16 or below!");
                                    }
                                    else {
                                        broadcast("Dealer " + turn[play].getname() +" had a " + turn[play].gethand() + "!");
                                        broadcast("and decided to stand on " + turn[play].gettotal());
                                        end= true;
                                    }
                                }
                                else {
                                    broadcast(turn[play].getname() + " decided to stand on " + turn[play].gettotal());
                                    displayhand();
                                    play= play - 1; //go to next player turn
                                }
                            }
                            else { //if it is not players turn
                                    out.println("It is not yet your turn to go!");
                            }
                        } //end of stand cmd
                        else if (msg.startsWith("Check")) { //check hand
                            if(turn[play].checkdealer()== true && turn[play].getname()== username) {
                                out.println(turn[play].gethand());
                            }
                        } //end of check cmd

                        if(end== true) { //finish game
                            for(int i= 4; i >= 1; i--) { //announce results
                                if(turn[i]== null) {
                                    end= false;
                                }
                                if(turn[i].gettotal()> turn[0].gettotal() && turn[i].gettotal()<= 21) { //compare against dealer
                                    broadcast(turn[i].getname() + " has beaten the dealer!");
                                }
                                else if(turn[i].gettotal()== turn[0].gettotal() && turn[i].gettotal()<= 21) {
                                    broadcast(turn[i].getname() + " has pushed with the dealer!");
                                }
                                else if (turn[i].gettotal() > 21) {
                                    broadcast(turn[i].getname() + " had went over and busted!");
                                }
                                else if(turn[0].gettotal()> 21) {
                                    broadcast("Dealer " + turn[0].getname() + " had went over and busted, so " + turn[i].getname() + " won!");
                                }
                                else {
                                    broadcast(turn[i].getname() + " got less than and lost to the dealer!");
                                }
                            } //end of results
                            end= false;
                            mid= false;
                        }
                    } //end of while game
                }
            }
            catch (IOException e){
                shutdown();
            }

        }
        public void displayhand() {
            broadcast("-------------------------\n");
            if((play-1)== 0 || turn[play-1]== null) {
                broadcast("Dealer " + turn[0].getname() + " has " + turn[0].gethand() + "\n");
            }
            else {
                broadcast("Dealer " + turn[0].getname() + " is showing " + turn[0].dealershows() + "\n");
            }

            for(int i= 4; i!= 0; i--) { //display hands after hit

                if(turn[i]== null) {
                    break;
                }
                broadcast(turn[i].getname() + "'s Hand: " + turn[i].gethand() + "      Total: " + turn[i].gettotal() + "\n");
            }
            if ((play-1)== 0 || turn[play-1]== null) {
                broadcast("Dealer " +turn[0].getname() + ": It's my turn! Will I hit or stand?");
                broadcast("-------------------------\n"); //end of hand display
            }
            else if (turn[play-1]!= null){
                broadcast("Dealer " + turn[0].getname() + ": It's " + turn[play-1].getname() + "'s turn! Will you hit or stand?");
                broadcast("-------------------------\n"); //end of hand display
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