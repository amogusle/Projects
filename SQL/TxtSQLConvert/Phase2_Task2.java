import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Phase2_Task2{
    public static void main(String[] args){
        try{
            File file = new File(args[0] + ".txt"); //opens text file
            Scanner scan = new Scanner(file);
            do {
                ArrayList<String> output = new ArrayList<String>();
                String[] line = scan.nextLine().split(",");

                for (int i = 0, k = line.length; i < k; i++){
                    if (isInt(line[i].trim()) == true || isFloat(line[i].trim()) == true || line[i].trim().equals("NULL")) { //checks datatype and adds to output
                        output.add(line[i].trim());
                    }
                    else{
                        output.add("'" + line[i].trim() + "'"); //adds string to output
                    }
                }
                String out = String.join(", ", output); //joins output with commas
                while(true) { //writes to sql file
                    try {

                        FileWriter outputFile = new FileWriter(args[0] + ".sql", true);
                        outputFile.write("INSERT INTO " + args[0] + " VALUES (" + out + ");" + "\n");
                        outputFile.close();
                        break;
                    } catch (IOException e){
                        File newFile = new File(args[0] + ".sql");
                        newFile.createNewFile();
                    }
                }
            }
            while (scan.hasNextLine());

            FileWriter commitFile = new FileWriter(args[0] + ".txt", true);
            commitFile.write("commit;");
            commitFile.close();

        } 
        catch (Exception e){
            System.out.println("Error.");
        }
    }
    public static boolean isInt(String x){
        try {
            int i = Integer.parseInt(x);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    public static boolean isFloat(String x){
        try {
            float f = Float.parseFloat(x);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
