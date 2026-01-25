public class Students implements Comparable<Students> {

		private int studentID;
		private String firstName;
		private String lastName;
		private double gpa;

		public Students(String first, String last, int id, double gpa) {
				this.firstName = first;
				this.lastName = last;
				this.studentID = id;
				this.gpa = gpa;
		}

		public String getFirstName() {
				return this.firstName;
		}

		public String getLastName() {
				return this.lastName;
		}

		public int getID() {
				return this.studentID;
		}

		public double getGPA() {
				return this.gpa;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Students) {
				Students o = (Students)other;
				if (o.getFirstName().equals(firstName) &&
					o.getLastName().equals(lastName) &&
					o.getID() == studentID &&
					Math.abs(o.getGPA() - gpa) < 0.005 )
						return true;
			}
			return false;

		}

		@Override
		public int compareTo(Students other) {
			return this.studentID - other.getID();
		}


} // end class
