public class Worker extends HumanBeing{
    protected String name;
    protected double hrlypay;

    public Worker(String name, double hrlypay) {
        super(name);
        this.hrlypay= hrlypay;
    }

    public double getPay() {
        return this.hrlypay + 0.00;
    }
}
