public class Animal {
    protected String name;

    public Animal(String name) {
        this.name= name;
    }

    public Animal() {

    }
    public static void main(String[] args) {
        Dog Jake= new Dog("Jake", 3.5, 80);
        Dog ScoobyDoo= new Dog("Scooby", 4.8, 124);
        Terrier Crew= new Terrier("Crew", 6.2, 185);
        Bird Mordecai= new Bird();
        Animal[] Animals= new Animal[4];
        Animals[0]= Jake;
        Animals[1]= ScoobyDoo;
        Animals[2]= Crew;
        Animals[3]= Mordecai;

        System.out.println(Animals[0].name + " " + Animals[1].name + " " + Animals[2].name);
    }
}

    