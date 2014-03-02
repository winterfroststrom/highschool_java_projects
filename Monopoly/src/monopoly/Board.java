/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
public class Board {
    int[] tileOwners = new int[28];
    int[] houses = new int[28];
    int goBonus = 200;
    Monopoly game;
    
    public Board(Monopoly game) {
        this.game = game;
    }


    public void moveTile(Player p){
        p.tileBPlace = game.dice + p.tileBPlace;
        if(p.tileBPlace > 39) p.changeMoney(-goBonus);
        p.tileBPlace %= 40;
    }

    public String getTileName(int tile){
        switch(tile){
            case 0: return "Go";
            case 1: return Properties.P1.name;
            case 2: return "Community Chest";
            case 3: return Properties.P2.name;
            case 4: return "Income Tax";
            case 5: return Properties.R1.name;
            case 6: return Properties.P3.name;
            case 7: return "Chance";
            case 8: return Properties.P4.name;
            case 9: return Properties.P5.name;
            case 10: return "Jail";
            case 11: return Properties.P6.name;
            case 12: return Properties.U1.name;
            case 13: return Properties.P7.name;
            case 14: return Properties.P8.name;
            case 15: return Properties.R2.name;
            case 16: return Properties.P9.name;
            case 17: return "Community Chest";
            case 18: return Properties.P10.name;
            case 19: return Properties.P11.name;
            case 20: return "Free";
            case 21: return Properties.P12.name;
            case 22: return "Chance";
            case 23: return Properties.P13.name;
            case 24: return Properties.P14.name;
            case 25: return Properties.R3.name;
            case 26: return Properties.P15.name;
            case 27: return Properties.P16.name;
            case 28: return Properties.U2.name;
            case 29: return Properties.P17.name;
            case 30: return "To Jail";
            case 31: return Properties.P18.name;
            case 32: return Properties.P19.name;
            case 33: return "Community Chest";
            case 34: return Properties.P20.name;
            case 35: return Properties.R4.name;
            case 36: return "Chance";
            case 37: return Properties.P21.name;
            case 38: return "Luxury Tax";
            case 39: return Properties.P22.name;
            default: return null;
        }
    }

    public int b2p(int b){
        switch(b){
            case 1: return 0;
            case 3: return 1;
            case 5: return 22;
            case 6: return 2;
            case 8: return 3;
            case 9: return 4;
            case 11: return 5;
            case 12: return 26;
            case 13: return 6;
            case 14: return 7;
            case 15: return 23;
            case 16: return 8;
            case 18: return 9;
            case 19: return 10;
            case 21: return 11;
            case 23: return 12;
            case 24: return 13;
            case 25: return 24;
            case 26: return 14;
            case 27: return 15;
            case 28: return 27;
            case 29: return 16;
            case 31: return 17;
            case 32: return 18;
            case 34: return 19;
            case 35: return 25;
            case 37: return 20;
            case 39: return 21;
            default: return -1;
        }
    }

    public Properties ptile(int tile){
        switch(tile){
            case 1: return Properties.P1;
            case 3: return Properties.P2;
            case 5: return Properties.R1;
            case 6: return Properties.P3;
            case 8: return Properties.P4;
            case 9: return Properties.P5;
            case 11: return Properties.P6;
            case 12: return Properties.U1;
            case 13: return Properties.P7;
            case 14: return Properties.P8;
            case 15: return Properties.R2;
            case 16: return Properties.P9;
            case 18: return Properties.P10;
            case 19: return Properties.P11;
            case 21: return Properties.P12;
            case 23: return Properties.P13;
            case 24: return Properties.P14;
            case 25: return Properties.R3;
            case 26: return Properties.P15;
            case 27: return Properties.P16;
            case 28: return Properties.U2;
            case 29: return Properties.P17;
            case 31: return Properties.P18;
            case 32: return Properties.P19;
            case 34: return Properties.P20;
            case 35: return Properties.R4;
            case 37: return Properties.P21;
            case 39: return Properties.P22;
            default: return null;
        }
    }

    public String etile(int tile){
        switch(tile){
            case 0: return "Go";
            case 2: return "Community Chest";
            case 4: return "Income Tax";
            case 7: return "Chance";
            case 10: return "Jail";
            case 17: return "Community Chest";
            case 20: return "Free";
            case 22: return "Chance";
            case 30: return "To Jail";
            case 33: return "Community Chest";
            case 36: return "Chance";
            case 38: return "Luxury Tax";
            default: return null;
        }
    }

    public void tile(Player player){
        Properties p = ptile(player.tileBPlace);
        String s = etile(player.tileBPlace);
        if(s==null) event(p,player); else event(s,player);
    }

    public void event(String s,Player player){
        if(s.equals("Go"))
            game.changeMoney(player.pid,-goBonus);
        else if(s.equals("To Jail"))
            player.jailed();
        else if(s.equals("Income Tax"))
            game.changeMoney(player.pid,200);
        else if(s.equals("Luxury Tax"))
            game.changeMoney(player.pid,100);
        else if(s.equals("Community Chest")){
            game.cc();
        } else if(s.equals("Chance")){
            game.c();
            return;
        }
        game.setEndOn();
    }

    public void event(Properties p, Player player){
        if(tileOwners[p.ordinal()]==0){
            game.buyOption(player,p);
        } else {
            if(p.set == 10) {
                if(tileOwners[26] == tileOwners[27]){
                    player.changeMoney(game.dice * 10);
                    game.players[tileOwners[p.ordinal()]-1].changeMoney(-game.dice * 10);
                } else {
                    player.changeMoney(game.dice * 4);
                    game.players[tileOwners[p.ordinal()]-1].changeMoney(-game.dice * 10);
                }
            } else if(p.set == 9) {
                railroadCost(player);
            } else {
                propCost(player,p);
            }
            game.setEndOn();
        }
    }

    public void propCost(Player player, Properties p){
        int owner = tileOwners[b2p(player.tileBPlace)] -1;
        switch(houses[p.ordinal()]){
            case 0: int multi = 1;
                if(tileOwners[p.getFirst()] == tileOwners[p.getSecond()] &&
                        tileOwners[p.getThird()] == tileOwners[p.getSecond()])
                    multi = 2;
                game.changeMoney(player.pid,p.rent*multi);
                game.changeMoney(owner,-p.rent*multi);
                break;
            case 1: game.changeMoney(player.pid,p.h1);
                game.changeMoney(owner,-p.h1);
                break;
            case 2: game.changeMoney(player.pid,p.h2);
                game.changeMoney(owner,-p.h2);
                break;
            case 3: game.changeMoney(player.pid,p.h3);
                game.changeMoney(owner,-p.h3);
                break;
            case 4: game.changeMoney(player.pid,p.h4);
                game.changeMoney(owner,-p.h4);
                break;
        }
    }

    public void railroadCost(Player player){
        int owner = tileOwners[b2p(player.tileBPlace)];
        int number = 0;
        for(int i = 22;i<26;i++)
            if(owner == tileOwners[i])
                number++;
        switch(number){
            case 1: game.changeMoney(player.pid,25);
                game.changeMoney(owner-1,-25);
                break;
            case 2: game.changeMoney(player.pid,50);
                game.changeMoney(owner-1,-50);
                break;
            case 3: game.changeMoney(player.pid,100);
                game.changeMoney(owner-1,-100);
                break;
            case 4: game.changeMoney(player.pid,200);
                game.changeMoney(owner-1,-200);
                break;
        }
    }
}
