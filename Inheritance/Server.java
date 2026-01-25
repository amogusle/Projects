public class Server extends Worker{
    protected String name;
    protected double hrlypay;

    public Server(String name, double hrlypay) {
        super(name, hrlypay);
        this.name= name;
    }

    public static void jobDesc() {
        System.out.println("I take people's orders and serve them their food.");
    }
}
