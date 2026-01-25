import java.io.*;
import java.util.*;

public class DataTypeDetector {

    public static void main(String[] args) {
        String fileName = "phase1_fall2025.txt"; // Change this to your file path

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] values = line.split(",");

                for (String value : values) {
                    value = value.trim(); // Remove spaces
                    System.out.print(detectType(value) + " ");
                }
                System.out.println(); // Newline for each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to detect data type
    public static String detectType(String value) {
        // Check for Integer
        try {
            Integer.parseInt(value);
            return "Int";
        } catch (NumberFormatException e) {
            // Not an integer
        }

        // Check for Double
        try {
            Double.parseDouble(value);
            return "Double";
        } catch (NumberFormatException e) {
            // Not a double
        }

        // Otherwise, it's a String
        return "String";
    }
}
