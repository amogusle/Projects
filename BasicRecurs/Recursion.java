public class Recursion {

    /**
     * @author Danny Le
     * @since Monday March 25th, 2024
     */
    public static void main(String[] args) {
        Students Joe= new Students("Joe", "Mama", 123, 3.5);
        Students John= new Students("John", "John", 321, 2.9);
        Students Josh= new Students("Josh", "Luu", 999, 3.2);
        Students[] students= {Joe, John, Josh};
        System.out.println(singleDigitMultiply(5, 4));
        //System.out.println(longmultiplication(124, 16));
        System.out.println(findMinimum(students, 3));
    }

    /**
     * @return returns the product of 2 numbers by repetitively adding the same number through recursion
     */    
    public static int singleDigitMultiply(final int i1, final int i2) {
        if (i1== 0 || i2== 0) {     //if theres a zero, then no need for recursion and return 0
            return 0;
        }
        else if (i1== 1) {      //if theres a one, return the other number and no need for recursion
            return i2;
        }
        else if (i2== 1) {
            return i1;
        }
        else {
            return singleDigitMultiply(i1- 1, i2) + i2;
        }
    }

    /**
     * @return returns the minimum student by checking the difference of their id numbers
     */   
    public static Students findMinimum(final Students[] students, int numStudents) {
        if (numStudents== 0) {
            return students[numStudents];
        }
        else {
            Students studenta= students[students.length- 1];
            Students studentb= findMinimum(students, numStudents- 1);
            
            if (studenta.compareTo(studentb) < 0) {     //if compareTo value is less than 0, return studentb and vis versa
                return studenta;
            }
            else {
                return studentb;
            }     
        }
    }

    /**
     * @return returns the product of 2 or more digit numbers through long multiplication using recursion
     */   
    public static int longmultiplication(final int i1, final int i2) {
        if (i1== 0 || i2== 0) {
            return 0;
        }
        else if (i1== 1) {
            return i2;
        }
        else if (i2== 1) {
            return i1;
        }
        else {
            return longmultiplication((i1%10)- 1, i2)+ i2+ longmultiplication(((i1%100))- 1, i2) + longmultiplication((((i1%1000)%100)%10)- 1, i2) + longmultiplication(((((i1%10000)%1000)%100)%10)- 1, i2) + longmultiplication((((((i1%100000)%10000)%1000)%100)%10)- 1, i2);
        }
    }
}
