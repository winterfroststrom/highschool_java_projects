/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
import java.util.LinkedList;

public class CC {
    Monopoly mono;
    boolean gojfc = true;
    LinkedList<Integer> deck;

    public CC(Monopoly mono) {
        this.mono = mono;
        deck = new LinkedList<Integer>();
        for(int i = 1;i<=16;i++){
            deck.add(i);
        }
        java.util.Collections.shuffle(deck);
    }

    public void event(){
        getEvent(getNextEvent());
    }

    public int getNextEvent(){
        int i = deck.removeFirst();
        if(i != 4)
            deck.addLast(i);
        return i;
    }

    public void getEvent(int event){
        String s = "";
        switch(event){
            case 1: s = "Advance to Go (Collect $200)";
                mono.players[mono.turn].tileBPlace = 0;
                mono.changeMoney(mono.turn, -mono.board.goBonus);
                break;
            case 2: s = "Bank error in your favor - collect $75";
                mono.changeMoney(mono.turn, -75);
                break;
            case 3: s = "Doctor's fees – Pay $50";
                mono.changeMoney(mono.turn, 50);
                break;
            case 4: s = "Get out of jail free – this card may be kept until needed, or sold";
                mono.players[mono.turn].gojfc1 = true;
                gojfc = false;
                break;
            case 5: s = "Go to jail – go directly to jail – Do not pass Go, do not collect $200";
                mono.players[mono.turn].jailed();
                break;
            case 6: s = "It is your birthday Collect $10 [£10] from each player";
                for(int i = 0;i<mono.numberPlayers;i++){
                    if(!mono.bankrupt[i]){
                        mono.changeMoney(i, 10);
                        mono.changeMoney(mono.turn, -10);
                    }
                }
                break;
            case 7: s = "Income Tax refund – collect $20";
                mono.changeMoney(mono.turn, -20);
                break;
            case 8: s = "Life Insurance Matures – collect $100";
                mono.changeMoney(mono.turn, -100);
                break;
            case 9: s = "Pay Hospital Fees of $100";
                mono.changeMoney(mono.turn, 100);
                break;
            case 10: s = "Pay School Fees of $50";
                mono.changeMoney(mono.turn, 50);
                break;
            case 11: s = "Receive $25 Consultancy Fee";
                mono.changeMoney(mono.turn, -25);
                break;
            case 12: s = "You are assessed for street repairs – $40 per house, $115 per hotel";
                for(int i = 0;i < mono.board.tileOwners.length;i++)
                    if(mono.board.tileOwners[i] == mono.turn+1)
                        mono.changeMoney(mono.turn, (mono.board.houses[i] == 5)?115:40*mono.board.houses[i]);
                break;
            case 13: s = "You have won second prize in a beauty contest– collect $10";
                mono.changeMoney(mono.turn, -10);
                break;
            case 14: s = "You inherit $100";
                mono.changeMoney(mono.turn, -100);
                break;
            case 15: s = "From sale of stock you get $50";
                mono.changeMoney(mono.turn, -50);
                break;
            case 16: s = "Holiday Fund matures - Receive $100";
                mono.changeMoney(mono.turn, -100);
                break;
        }
        mono.event.setText(s);
    }


}