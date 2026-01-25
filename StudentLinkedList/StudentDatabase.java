import org.w3c.dom.Node;
/**
     * @author Danny Le
     * @since Sunday April 21st, 2024
     */

public class StudentDatabase<T> {
    private Node firstStudent;
	private Node lastStudent;
	private int  size;
/** 
 *  @param firstStudent first student in database
 *  @param lastStudent last student in database
 *	@param size size of database
 *	@since Sunday April 21st, 2024	
*/
    public StudentDatabase() {
        this.firstStudent= null;
        this.lastStudent= null;
        this.size= 0;
    }

/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @see Node
 * @return prints data of all students
*/
	public void getAllData() {
		Node current= firstStudent;
		//
		for (int i= 0; i < getSize();) {
			current.getData();
			current= current.getNext();
			i++;
		} 
	}

/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @return size of database
*/
    public int getSize() {
        return this.size;
    }

/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @see Node
 * @requires a valid student object
 * @effects adds student to database
*/
    public void add(Student student) {
		Node newStudent = new Node(student);

		if (size == 0){
			firstStudent = newStudent;
			lastStudent = newStudent;
			size++;
			return;
		}
            
        lastStudent.setNext(newStudent);
		this.lastStudent = newStudent;
		size++;
	}

/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @see Node
 * @see add
 * @requires valid index that is within database size and valid student object
 * @effects adds student to database at given index
*/
	public void add(int index, Student student) {
		Node newStudent = new Node(student);

		if ( index  > size  ) {
			throw new IndexOutOfBoundsException("Index invalid");
		}

		if ( index == size) {
			add(student);
			return;
		}

		if (index == 0) {
			Node tempNext = firstStudent;
			this.firstStudent = newStudent;
			newStudent.setNext(tempNext);
			size++;
			return;
		}
		
		Node currentStudent = firstStudent;
		int counter= 0;
		while ( counter < index - 1 ) {
			currentStudent= currentStudent.getNext();
			counter++;
		}

		Node tempNext = currentStudent.getNext();
		currentStudent.setNext(newStudent);
		newStudent.setNext(tempNext);
		size++;
	}
/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @see Node
 * @requires valid index and data must have a least one student inside it
 * @effects removes a student from index and prints data of who's been removed
*/
	public void remove(int index) {
		if ( index  >= size  )//if index is bigger than size
				throw new IndexOutOfBoundsException("Index invalid");

		if (index == 0) {
			Node tempNext = this.firstStudent.getNext();
			firstStudent.getData();
			firstStudent.setNext(null);
			this.firstStudent = tempNext;
			size--;
			
		}

		else {
		Node currentStudent = firstStudent;
		int counter= 0;
			while ( counter < index - 1 ) {//loops to index
				currentStudent = currentStudent.getNext();
				counter++;
			}

			Node tempNext = currentStudent.getNext().getNext();
			currentStudent.getNext().setNext(null);
			currentStudent.setNext(tempNext);
			
			if (index == size-1) {
				lastStudent.getData();
				this.lastStudent = currentStudent;
				size--;
			}
		}
	}

/**
 * @author Danny Le
 * @since Sunday April 21th, 2024
 * @see Node
 * @requires positive student ID number
 * @return returns index of the studentID that's requested, if it is not found, it prints -1
*/
    public int indexOf(int studentID) {
        int currentID= 0;
        int index= -1;

         Node currentStudent= firstStudent;
			
		while (currentStudent != null) {
			currentID= currentStudent.studentID;
			currentStudent= currentStudent.getNext();
			index++;

			if (currentID == studentID) {
				return index;
			}	
		}
			
		return index;
	}//indexOf end

	/**
     * @author Danny Le & Dr. Summa
     * @since Sunday April 21st, 2024
     */
		private class Node {
			private Node nextNode;
			private int studentID;
			private String sex;
			private String ethnicGroup;
			private String name;
			private String program;
			private String academicPlan;
			private String subPlan;
			private int    strtLevel;
			private double total;
			private double gpa;
			private String fafsa;
			private double takePrgrs;
			private double financialNeed;

			private Node(Student student) {
				this.nextNode= null;
				this.studentID = student.getStudentID();
				this.sex = student.getSex();
				this.ethnicGroup = student.getEthnicGroup();
				this.name = student.getName();
				this.program = student.getProgram();
				this.academicPlan = student.getAcademicPlan();
				this.subPlan = student.getSubPlan();
				this.strtLevel = student.getStrtLevel();
				this.total = student.getTotal();
				this.gpa = student.getGPA();
				this.fafsa = student.getFAFSA();
				this.takePrgrs = student.getTakePrgrs();
				this.financialNeed = student.getFinancialNeed();
			}

			private void getData() {
				System.out.println("Student ID: " + studentID + ", Sex: " + sex + ", Ethnicity: " + ethnicGroup  + ", Name: " + name + ", Program: " + program + ", Academic Plan: " + academicPlan + ", Sub Plan: " + subPlan + ", Start Level: " + strtLevel + ", Total: " + total + ", GPA: " + gpa + ", Fasfa: " + fafsa + ", Taken Progress: "+ takePrgrs + ", Finanacial Need: " + financialNeed);
			}

			private Node getNext() {
				return this.nextNode;
			}

			private void setNext(Node next) {
				this.nextNode = next;
			}

		}//node end
}//database end
