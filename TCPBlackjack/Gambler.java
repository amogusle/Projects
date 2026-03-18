import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

public class Gambler implements Runnable {
    private Socket client;
    private boolean done;
    private BufferedReader in;
    private PrintWriter out;
    private static int port;
    private static String host;

    @Override
    public void run() {
        try {
            client = new Socket(host, port);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            InputHandler inputhand = new InputHandler();
            Thread input = new Thread(inputhand);
            input.start();
            String inmsg;

            while((inmsg = in.readLine()) != null) {
                System.out.println(inmsg);
            }
        } 
        catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;

        try{
            in.close();
            out.close();
            
            if(!client.isClosed()) {
                client.close();
            }
        } 
        catch (IOException e) {
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
                    if(message.equals("Bye")) {
                        inReader.close();
                        shutdown();
                    } 
                    else {
                        out.println(message);
                    }
                }
            } 
            catch (IOException e) {
                shutdown();
            }
        }
    }

    public static void main(String[] args) { //runs client
        host = args[0];
        port = Integer.parseInt(args[1]);
        Gambler player = new Gambler();
        player.run();
    }
}