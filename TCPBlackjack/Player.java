public class Player {
    private String name;
    private int cardamt;
    private String[] hand;
    private int total;
    private boolean dealer;

    public Player(String user) {
        this.name= user;
        this.cardamt= 0;
        this.hand= new String[0];
        this.total= 0;
    }
    
    void becomedealer() {
        this.dealer= true;
    }

    void add(String card) {
        switch(card) {
            case("2"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 2;
                this.hand[this.cardamt]= card;
            case("3"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 3;
                this.hand[this.cardamt]= card;
            case("4"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 4;
                this.hand[this.cardamt]= card;
            case("5"):
                this.cardamt= this.cardamt + 1;    
                this.total= this.total+ 5;
                this.hand[this.cardamt]= card;
            case("6"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 6;
                this.hand[this.cardamt]= card;
            case("7"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 7;
                this.hand[this.cardamt]= card;
            case("8"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 8;
                this.hand[this.cardamt]= card;
            case("9"):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 9;
                this.hand[this.cardamt]= card;
            case("10" ):
            case("J" ):
            case("Q" ):
            case("K" ):
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 10;
                this.hand[this.cardamt]= card;
            case("A"):
                this.cardamt= this.cardamt + 1;
                if((this.total+ 11) <= 21) {
                    
                    this.total= this.total+ 11;
                }
                else{
                    this.total= this.total+ 1;
                }
                this.hand[this.cardamt]= card;
        }
    }
    String getname() {
        return this.name;
    }
    String gethand() {
        String handmsg= "";
        for(int i= 0; i < this.hand.length; i++) {
            handmsg= handmsg + this.hand[i] + "+ ";
        }
            handmsg= handmsg + this.hand[this.hand.length];
        return handmsg;
    }   
    String dealershows() {
        return this.hand[1];
    }
    int gettotal() {
        return this.total;
    }
}
