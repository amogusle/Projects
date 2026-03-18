public class PetiteBete {
    private int hp;
    private int speed;
    private int attack;
    private String name;
    private int level;

    public PetiteBete(String newname, int newlevel, int newhp, int newattack, int newspeed) {
        this.name= newname;
        this.level= newlevel;
        this.hp= newhp;
        this.attack= newattack;
        this.speed= newspeed;
    }
    
    String getname() {
        return this.name;
    }

    int getlevel() {
        return this.level;
    }

    int gethp() {
        return this.hp;
    }

    int getattack() {
        return this.attack;
    }

    int getspeed() {
        return this.speed;
    }

    void setname(String setname) {
        this.name= setname;
    }

    void setlevel(int setlevel) {
        this.level= setlevel;
    }

    void sethp(int sethp) {
        this.hp= sethp;
    }

    void setattack(int setattack) {
        this.attack= setattack;
    }

    void setspeed(int speed) {
        this.speed= speed;
    }

    public void getHit(int amountOfHit) {
        this.hp= this.hp - amountOfHit;
    }
    
    public void attack(PetiteBete monster) {
        monster.getHit(this.attack); 
        System.out.println(this.name + " has attacked " + monster.name + " for " + this.attack + " dmg.");
        System.out.println(monster.name + " is now at " + monster.hp + " hp.");
    }

    public void fasterattack(PetiteBete monster) {
        if (this.speed > monster.speed) {//method to have faster monster to attack
            this.attack(monster);
        }
    }
    public void slowerattack(PetiteBete monster) {//method to have slower monster to attack
        if (this.speed > monster.speed) {
            monster.attack(this);
        }
    }
} //end of class bracket