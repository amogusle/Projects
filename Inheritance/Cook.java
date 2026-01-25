public class Cook extends Worker{
    protected String name;
    protected double hrlypay;

    public Cook(String name, double hrlypay) {
        super(name, hrlypay);
        this.name= name;
    }

    public static void jobDesc() {
        System.out.println("I prepare and cook stuff for work.");
    }
}
