public class DogTester {
    public static void main(String[] args) {
        Dog Jake= new Dog("Jake", 3.5, 80);
        Dog ScoobyDoo= new Dog("Scooby", 4.8, 124);
        Terrier Crew= new Terrier("Crew", 6.2, 185);
        Dog[] doggos= new Dog[3];
        doggos[0]= Jake;
        doggos[1]= ScoobyDoo;
        doggos[2]= Crew;

        System.out.println(doggos[0].name + " " + doggos[1].name + " " + doggos[2].name);
    }
}