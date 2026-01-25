public class Scenario {
    public static void main(String[] args) {
        Server Tom= new Server("Tom", 3.50); //subclass of Worker and Worker is subclass of HumanBeing
        Cook Walter= new Cook("Walter", 12.50); //another subclass of Worker
        HumanBeing Louis= new Student("Louis", 3.52);

        System.out.printf(Tom.name +" makes $%.2f as a server. ", Tom.getPay()); //we are able to inherit the getPay method from the Worker superclass and allows us to use it
        Server.jobDesc();
        System.out.printf(Walter.name + " makes $%.2f as a cook. ", Walter.getPay()); 
        Cook.jobDesc(); 
        //in this case we have multiple different workers in which they'll have different job descriptions and pay
        //this allows us to neatly create worker objects so that we can organize them in a restraurant or workplace type setting
        System.out.printf(Louis.name + "'s GPA is " + ((Student) Louis).getGPA() + ".");
    }
}
