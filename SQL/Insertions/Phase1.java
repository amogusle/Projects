import java.io.*;
import java.util.*;

public class Phase1 {
    public static void main(String[] args) {
        try {
        //Create a file to write results to
            FileWriter outfile = new FileWriter("phase1_output.txt");
            //Read an input file
            File infile = new File("phase1_fall2025.txt");
            Scanner input = new Scanner(infile);
            //Read the file line by line.
            while (input.hasNextLine()) { //while there's a line to read
                String line = input.nextLine(); //store that line in a;
                String variable;
                outfile.write(processLine(line)); //write our data processing to the outfile
                outfile.write("\n");
            }
            outfile.close(); // close the outfile
            input.close(); // close the input file
        } 
        catch (FileNotFoundException e) {
            //Need to catch FNFE for FileWriter and Scanner
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            //Need to catch IOE for FileWriter and Scanner
            System.out.println(e.getMessage());
        }
    }

    public static String processLine(String line) {
            String output; //this will be the "clean" data that's returned
            ArrayList<String> cleanValues = new ArrayList<String>(); 
            /* I will break the line into a series of values. I'll store these values in a list. */
            String[] values = line.split(","); // Break the line into values that are divided by a comma.

            for (String value : values) { 
                /* I can iterate through the values, do some additional cleaning/manipulation if I want, and then add to my list. */
                String newValue = value.trim(); //Remove leading or trailing whitespace
                newValue = datatype(newValue);
                cleanValues.add(newValue);
            }
        output = String.join(", ", cleanValues); 
        /* Produce my output string by joining my clean values on the pipe character.
        I could use another character if I wanted to. */
        return output;
    }

    public static String datatype(String type) {
        try {
            Integer.parseInt(type);
            return "int";
        }
        catch (NumberFormatException e) {
        }

        try {
            Double.parseDouble(type);
            return "double";
        }
        catch (NumberFormatException e) {
        }
        try {
            Float.parseFloat(type);
            return "float";
        }
        catch (NumberFormatException e) {
            return "string";
        }
    }
}