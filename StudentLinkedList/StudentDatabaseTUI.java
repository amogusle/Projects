import java.util.Scanner;
import java.util.Random;
/**
     * @author Danny Le
     * @since Sunday April 21th, 2024
     * @see StudentDatabase 
*/
public class StudentDatabaseTUI extends StudentDatabase<Student>{
    StudentDatabase<Student> database;
    //database instance

    public StudentDatabaseTUI(StudentDatabase<Student> database) {
        this.database= database;
    }//data is provided by the StudentDatabase

    /**
     * @author Danny Le
     * @since Sunday April 21th, 2024
     * @see randomStudent
     * @see Student
     * @see add
     * @see indexOf
     * @see remove
     * @see getData
    */
    public void run() {
        Scanner input = new Scanner (System.in);
        //scanner to detect inputs by user
            while (true) {
            //loops to keep program running and keeps data
                System.out.println("Please Enter a Choice:");
                System.out.println("");
                System.out.println("1) Create a new student record and add it to the database");
                System.out.println("2) Find a student record's index based on the Student's ID");
                System.out.println("3) Delete a student record based on its index in the database");
                System.out.println("4) Print the database");
                System.out.println("5) Exit Program");

                
                if (input.hasNextInt()) {//allows reinputs during loops
                    int userinput= input.nextInt();
                    input.nextLine();
                    
                    if (userinput== 1) {//option 1 to make student and add to database
                        System.out.println("1) Generate random student");
                        System.out.println("2) Manually create student");
                        System.out.println("3) Generate empty student");

                        int randormake= input.nextInt();

                        System.out.println("Choose the student's index:");
                        int index= input.nextInt();

                        Student student= new Student();
                        if (randormake== 1) {//randomly set Student data
                            student= randomStudent();
                            this.add(index, student);//adds student to database
                        }
                        else if (randormake== 2) {//manually set Student data
                            System.out.println("Student ID:");
                            int ID= input.nextInt();
                            student.setStudentID(ID);//sets ID

                            System.out.println("Sex: M or F");
                            String gender= input.next();
                            student.setSex(gender);//sets gender

                            System.out.println("Ethnic Group");
                            String ethnic= input.nextLine();
                            student.setEthnicGroup(ethnic);//sets ethnic group
                            input.nextLine();

                            System.out.println("Full Name:");
                            String name= input.nextLine();
                            student.setName(name);//sets name
                            System.out.println(name);

                            System.out.println("Program:");
                            String program= input.nextLine();
                            student.setProgram(program);//sets program

                            System.out.println("Academic Plan:");
                            String acaplan= input.nextLine();
                            student.setName(acaplan);//sets academic plan

                            System.out.println("Sub Plan:");
                            String sub= input.nextLine();
                            student.setSubPlan(sub);//sets sub plan

                            System.out.println("Level:");
                            int lvl= input.nextInt();
                            student.setStrtLevel(lvl);//sets level

                            System.out.println("Total:");
                            double total= input.nextDouble();
                            student.setTotal(total);//sets total

                            System.out.println("GPA:");
                            double gpa= input.nextDouble();
                            student.setGPA(gpa);//sets gpa

                            System.out.println("Fafsa: Y or N");
                            String fafsa= input.next();
                            student.setFAFSA(fafsa);//sets fafsa

                            System.out.println("Take Progress:");
                            double take= input.nextDouble();
                            student.setTakePrgrs(take);//sets taken progress

                            System.out.println("Financial Need:");
                            double finneed= input.nextDouble();
                            student.setFinancialNeed(finneed);//sets financial need

                            this.add(index,student);//adds student to database
                        }
                        else if (randormake== 3) {//empty set of Student data
                            this.add(index, student);//adds student to database
                        }
                    }
                    else if (userinput== 2) {//option 2 for Student ID filter
                        System.out.println("Please enter student ID:");
                        int ID= input.nextInt();//user input for ID to look for
                        System.out.println("Index #: " + indexOf(ID));
                    }
                    else if (userinput== 3) {//option 3 removes a student from database
                        System.out.println("Please enter index to be removed:");
                        int index= input.nextInt();//index input for student to be removed
                        this.remove(index);//removes student
                    }
                    else if (userinput== 4) {//option 4 prints all student data from database
                        getAllData();//prints all of database
                    }
                    else if (userinput== 5) {//option 5 exits out of program by breaking loop
                        break;
                    }
                    else {//tells if user enters unavailable input
                        System.out.println("Not an available option.");
                    }
                }
            }
        input.close();
    }
    /**
     *  @author Danny Le & Dr. Summa
     *  @since Monday April 22, 2024
     *  @return Returns the creation of a random generated Student
    */
    public static Student randomStudent() {

        String[] firstNames = {"Joe","Yeongsik","Louis","Becky","Lamar","Steve","Stacey","Issa","James","Joan","Haydar","Joachim","Chris","Pham","Stan","Alice","Bob","Josie","Jose","Kendra","T-Bob","Maria","Satoshi","Tyson","Lars","Nolan","Doug","Xavier","Francine","Ann","Sridhar","Bhupinder","Jason","Walter","Brian","Nancy","Michael","Thien"};

        String[] middleInitials = {"A.","B.","C.","D.","E.","F.","G.","H.","I.","J.","K.","L.","M.","N.","O.","P.","Q.","R.","S.","T.","U.","V.","W.","X.","Y.","Z."};

        String[] lastNames = {"Jackson","Boudreaux","Thibodeaux","Landry","Nemoy","Kirk","Levitt","Pham","Nguyen","Wynn","Schwing","Johnson","Lee","Abdelguerfi","Yoo","Smith","Jones","Baker","Naquin","Nakamura","Keonnigsegg","Stuart","Tudor","Abromov","Jaeger","Jarndyce","Cooper","Dufresne","Singh","Grisham","Humbert","Einstein","Feynman","Marsalis","Fang","Chen","Charagundala"};

        String[] ethnicities = {"Asian","Hispanic/Latino","White","Black/African American","Two or more races","Native American","Not Specified"};
        String[] fafsaOrNot = {"Y","N"};

        String[] subPlans = {"BIOINFORM","CYBER","GAMEDEV",""};
        String[] genders = {"M","F"};

        Random rng = new Random();

        Student s = new Student();
        String name = "\"" + lastNames[rng.nextInt(lastNames.length)] + ", " + firstNames[rng.nextInt(firstNames.length)] + " " + middleInitials[rng.nextInt(middleInitials.length)] + "\"";
        double gpa = 1.0 + rng.nextDouble() * 3.0;
        String sex = genders[rng.nextInt(genders.length)];
        int studentID = rng.nextInt(999999) + 2600000;
        String ethnicGrp = ethnicities[rng.nextInt(ethnicities.length)];
        String fafsa = fafsaOrNot[rng.nextInt(fafsaOrNot.length)];
        String subPlan = subPlans[rng.nextInt(subPlans.length)];
        int strtLevel = rng.nextInt(40);
        double total = 4.0 * rng.nextInt(8);
        double takePrgrs = (double)(rng.nextInt(18)+1);
        double financialNeed = (double)(rng.nextInt(10000));
        s.setName(name);
        s.setGPA(gpa);
        s.setSex(sex);
        s.setStudentID(studentID);
        s.setEthnicGroup(ethnicGrp);
        s.setFAFSA(fafsa);
        s.setSubPlan(subPlan);
        s.setStrtLevel(strtLevel);
        s.setTotal(total);
        s.setTakePrgrs(takePrgrs);
        s.setFinancialNeed(financialNeed);

        return s;
    }

    public static void main (String[] args) {
        StudentDatabase<Student> model= new StudentDatabase<>();
        StudentDatabaseTUI userInterface= new StudentDatabaseTUI(model);
        userInterface.run();
    }
}
