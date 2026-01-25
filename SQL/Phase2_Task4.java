import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Phase2_Task4 {
    public static void main(String[] args) {
        HashMap<String, String> Physicians = new HashMap<>();
        HashMap<String, String> Nurses = new HashMap<>();
        HashMap<String, String> Beds = new HashMap<>();
        HashMap<String, Integer> Timecards = new HashMap<>();

        try {
            File phys= new File("Physician2.txt");
            Scanner readph= new Scanner(phys);
            File nurs= new File("Nurse2.txt");
            Scanner readn= new Scanner(nurs);
            File bed= new File("Bed2.txt");
            Scanner readb= new Scanner(bed);
            File time= new File("Timecard2.txt");
            Scanner readt= new Scanner(time);
            
            //read phys, add to Physicians

            //read nurs, add to Nurses

            //read bed, add to Beds

            //read time, add to Timecards
        }
        catch (Exception e) {

        }
    }

    public void NurseMonitoring(HashMap<String, String> Nurses, HashMap<String, String> Beds) {
        int i= 0;
        String[] nurse= new String[i];

        for (int x = 0; x < 99; x++) {
            if (Beds.get("B" + x) != "NULL") {
                nurse[i]= Nurses.get("B" + x);
            }
        }

        for (int y = 0; y < nurse.length; y++) {
            System.out.println(nurse[y]);
        }
    }

    public void PhysicianHours(HashMap<String, String>  Physicians, HashMap<String, Integer> Timecards) {
        int i= 0;
        String[] hoursWorked= new String[i];

        for (int x = 0; x < 99; x++) {
            if (Timecards.get("D" + x) != null) {
            hoursWorked[i]= "" + Timecards.get("D" + x);
            }
        }

        for (int y = 0; y < hoursWorked.length; y++) {
            System.out.println(hoursWorked[y]);
        }
    }
}
