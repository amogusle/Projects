import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class CartoonCharacterSerializer {
	
	private static ObjectOutputStream outputCartoon;
	private static ObjectInputStream inputCartoon;
	private static PrintWriter writer;
	
	public static void serializeCharacter(CartoonCharacter character) {
		try {
			// Create ObjectOutputStream with FileOutputStream
			outputCartoon= new ObjectOutputStream(new FileOutputStream("cartoonFile.ser"));
			// Write the CartoonCharacter object to the ObjectOutputStream
			outputCartoon.writeObject(character);
			// Close the ObjectOutputStream to ensure proper resource management
			outputCartoon.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static CartoonCharacter deserializeCharacter() {
		CartoonCharacter cartoon = null;
		
		try {
			// Create ObjectInputStream with FileInputStream
			inputCartoon= new ObjectInputStream(new FileInputStream("cartoonFile.ser"));
			// Read the CartoonCharacter object from the ObjectInputStream and cast it
			while (true) {
				cartoon= (CartoonCharacter)inputCartoon.readObject();
				System.out.println(cartoon);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cartoon;
	}
	
	public static void printToFile(String stringToFile) throws FileNotFoundException {
		// Create PrintWriter with FileWriter
		writer= new PrintWriter("cartoonFile.ser");
		// Write the specified string to the file
		writer.write(stringToFile);
		// Print a formatted line to the file
		System.out.println(stringToFile);
		// Close the PrintWriter to ensure proper resource management
		writer.close();
	}

}
