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

public class C {
    Monopoly mono;
    boolean gojfc = true;
    LinkedList<Integer> deck;

    public C(Monopoly mono) {
        this.mono = mono;
        deck = new LinkedList<Integer>();
        for(int i = 1;i<=17;i++){
            deck.add(i);
        }
        java.util.Collections.shuffle(deck);
    }

    public void event(){
        getEvent(getNextEvent());
    }

    public int getNextEvent(){
        int i = deck.removeFirst();
        if(i != 8)
            deck.addLast(i);
        return i;
    }

    public void getEvent(int event){
        String s = "";
        switch(event){
            case 1: s = "Advance to Go (Collect $200)";
                mono.players[mono.turn].tileBPlace = 0;
                mono.changeMoney(mono.turn, -mono.board.goBonus);
                mono.setEndOn();
                break;
            case 2: s = "Advance to Illinois Ave.";
                mono.players[mono.turn].tileBPlace = 24;
                mono.board.tile(mono.players[mono.turn]);
                break;
            case 3: s = "<html>Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.</html>";
                if(mono.players[mono.turn].tileBPlace< 28 && mono.players[mono.turn].tileBPlace > 12){
                    mono.players[mono.turn].tileBPlace = 24;
                } else mono.players[mono.turn].tileBPlace = 12;
                mono.c3 = true;
                mono.roll.setEnabled(true);
                break;
            case 4:
            case 5: s = "<html>Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.</html>";
                railroad();
                break;
            case 6: s = "Advance to St. Charles Place – if you pass Go, collect $200";
                if(mono.players[mono.turn].tileBPlace > 11)
                    mono.changeMoney(mono.turn, -mono.board.goBonus);
                mono.players[mono.turn].tileBPlace = 11;
                mono.board.tile(mono.players[mono.turn]);
                break;
            case 7: s = "Bank pays you dividend of $50";
                mono.changeMoney(mono.turn, -50);
                mono.setEndOn();
                break;
            case 8: s = "Get out of Jail free – this card may be kept until needed, or traded";
                mono.players[mono.turn].gojfc2 = true;
                gojfc = false;
                mono.setEndOn();
                break;
            case 9: s = "Go back 3 spaces";
                mono.players[mono.turn].tileBPlace -= 3;
                mono.board.tile(mono.players[mono.turn]);
                break;
            case 10: s = "Go directly to Jail – do not pass Go, do not collect $200";
                mono.players[mono.turn].jailed();
                mono.setEndOn();
                break;
            case 11: s = "<html>Make general repairs on all your property – for each house pay $25 [£25] – for each hotel $100</html>";
                for(int i = 0;i < mono.board.tileOwners.length;i++)
                    if(mono.board.tileOwners[i] == mono.turn+1)
                        mono.changeMoney(mono.turn, (mono.board.houses[i] == 5)?100:25*mono.board.houses[i]);
                mono.setEndOn();
                break;
            case 12: s = "Speeding Fine $15";
                mono.changeMoney(mono.turn, 15);
                mono.setEndOn();
                break;
            case 13: s = "Take a trip to Reading Railroad – if you pass Go collect $200";
                if(mono.players[mono.turn].tileBPlace > 5)
                    mono.changeMoney(mono.turn, -mono.board.goBonus);
                mono.players[mono.turn].tileBPlace = 5;
                mono.board.tile(mono.players[mono.turn]);
                break;
            case 14: s = "Advance to Boardwalk";
                mono.players[mono.turn].tileBPlace = 39;
                mono.board.tile(mono.players[mono.turn]);
                break;
            case 15: s = "You have been elected chairman of the board – pay each player $50";
                for(int i = 0;i<mono.numberPlayers;i++){
                    if(!mono.bankrupt[i]){
                        mono.changeMoney(i, -50);
                        mono.changeMoney(mono.turn, 50);
                    }
                }
                mono.setEndOn();
                break;
            case 16: s = "Your building and loan matures – collect $150";
                mono.changeMoney(mono.turn, -150);
                mono.setEndOn();
                break;
            case 17: s = "You have won a crossword competition - collect $100";
                mono.changeMoney(mono.turn, -100);
                mono.setEndOn();
                break;
        }
        mono.event.setText(s);
    }

    public void railroad(){
        if(mono.players[mono.turn].tileBPlace < 5){
            mono.players[mono.turn].tileBPlace = 5;
        } else if(mono.players[mono.turn].tileBPlace < 15){
            mono.players[mono.turn].tileBPlace = 15;
        } else if(mono.players[mono.turn].tileBPlace < 25){
            mono.players[mono.turn].tileBPlace = 25;
        } else if(mono.players[mono.turn].tileBPlace < 35){
            mono.players[mono.turn].tileBPlace = 35;
        } else mono.players[mono.turn].tileBPlace = 5;
        if(mono.board.tileOwners[mono.turn] == 0)
            mono.board.tile(mono.players[mono.turn]);
        else {
            mono.board.railroadCost(mono.players[mono.turn]);
            mono.board.railroadCost(mono.players[mono.turn]);
            mono.setEndOn();
        }
    }
}
