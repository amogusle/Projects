//Danny Le
import java.io.*;
import java.util.*;

public class Task2 extends Thread { 
    public static HashMap<String, Integer> airports= new HashMap<>();
    
    public static void main(String[] args) throws FileNotFoundException {
        double start= System.nanoTime();

        Thread thread= new Thread(() -> {
            for (int i= 20; i<=40; i++) {
                read(i);
            }
        });

        thread.start();
                        
        for (int i= 1; i<=20; i++) {
            String data;
            File file= new File(String.format("itineraries%02d.csv", i));
            try (Scanner reader= new Scanner(file);) {
                while (reader.hasNextLine()) {
                    data= reader.nextLine();
                    String[] array= data.split(",");

                    if (array[10].equals("True")) {
                        airports.putIfAbsent(array[4], 0);
                        airports.put(array[4], airports.get(array[4]) + 1);
                    }
                }
            }
            catch (FileNotFoundException error) {
                return;
            }
                
        }
                        
        System.out.println("ATL: " + airports.get("ATL"));
        System.out.println("BOS: " + airports.get("BOS"));
        System.out.println("CLT: " + airports.get("CLT"));
        System.out.println("DEN: " + airports.get("DEN"));
        System.out.println("DFW: " + airports.get("DFW"));
        System.out.println("DTW: " + airports.get("DTW"));
        System.out.println("EWR: " + airports.get("EWR"));
        System.out.println("IAD: " + airports.get("IAD"));
        System.out.println("JFK: " + airports.get("JFK"));
        System.out.println("LAX: " + airports.get("LAX"));
        System.out.println("LGA: " + airports.get("LGA"));
        System.out.println("MIA: " + airports.get("MIA"));
        System.out.println("OAK: " + airports.get("OAK"));
        System.out.println("ORD: " + airports.get("ORD"));
        System.out.println("PHL: " + airports.get("PHL"));
        System.out.println("SFO: " + airports.get("SFO"));
                    
        double end= System.nanoTime();
        System.out.println("Finished: " + (end-start)/1e9);
    }

    public static void read(int i) {
        String data;
        File file= new File(String.format("itineraries%02d.csv", i));
        try (Scanner reader= new Scanner(file);) {
            while (reader.hasNextLine()) {
                data= reader.nextLine();
                String[] array= data.split(",");

                if (array[10].equals("True")) {
                    airports.putIfAbsent(array[4], 0);
                    airports.put(array[4], airports.get(array[4]) + 1);
                }
            }
        }
        catch (FileNotFoundException error) {
            return;
        }
    }
}
                
