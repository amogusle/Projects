public class Students extends HumanBeing{
    protected String name;
    protected double GPA;

    public Students(String name, double GPA) {
        super (name);
        this.name= name;
        this.GPA= GPA;
    }

    public double getGPA() {
        return this.GPA;
    }
}
