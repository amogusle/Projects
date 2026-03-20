import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int cardamt;
    private String[] hand;
    private int total;
    private boolean dealer;
    private int acecnt;

    public Player(String user) {
        this.name= user;
        this.cardamt= 0;
        this.hand= new String[10];
        this.acecnt= 0;
        this.total= 0;
    }
    
    void becomedealer() {
        this.dealer= true;
    }
    boolean checkdealer() {
        return dealer;
    }

    void add(String card) {
        switch(card) {
            case("2"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 2;
                break;
            case("3"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 3;
                break;
            case("4"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 4;
                break;
            case("5"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 5;
                break;
            case("6"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 6;
                break;
            case("7"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 7;
                break;
            case("8"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 8;
                break;
            case("9"):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 9;
                break;
            case("10" ):
            case("J" ):
            case("Q" ):
            case("K" ):
                this.hand[this.cardamt]= card;
                this.cardamt= this.cardamt + 1;
                this.total= this.total+ 10;
                break;
            case("A"):
                this.hand[this.cardamt]= card;
                if((this.total+ 11) <= 21) {
                    this.total= this.total+ 11;
                    this.acecnt= this.acecnt + 1;
                }
                else{
                    this.total= this.total+ 1;
                    this.acecnt= this.acecnt + 1;
                }
                this.cardamt= this.cardamt + 1;
                break;
        }
    }
    String getname() {
        return this.name;
    }
    String gethand() {
        String handmsg= "";
        for(int i= 0; i < this.hand.length;) {
            if(this.hand[i]== null) {
                break;
            }
            handmsg= handmsg + this.hand[i] + " ";
            i++;
        }
            
        return handmsg;
    }   
    String dealershows() {
        return this.hand[1];
    }
    void adjustace() {
        if(acecnt > 1 && (this.total- 20) < 21) {
            this.total= this.total - 20;
        }
        else if(acecnt > 1 && (this.total- 10) < 21) {
            this.total= this.total - 10;
        }
        else if(acecnt== 1) {
            this.total= this.total+ 1;
        }
        else {
        }
    }
    int gettotal() {
        adjustace();
        return this.total;
    }
}
