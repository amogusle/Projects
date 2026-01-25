public class Terrier extends Dog{
    protected String name;
    protected double height;
    protected double weight;
    protected boolean isCrabby;
    
    public Terrier(String name, double height, double weight) {
        super(name, height, weight);
    }

    public void move() {
        System.out.println("I'm fast as freak boiiiii!");
    }
}
