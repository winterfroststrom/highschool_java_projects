/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
import java.awt.Color;

public class Player {
    int tileBPlace;
    int money;
    private boolean jailed = false;
    static int id;
    final int pid;
    Monopoly base;
    boolean gojfc1 = false;
    boolean gojfc2 = false;
    int jc;

    public Player(Monopoly base) {
        this.base = base;
        pid = id;
        id++;
        tileBPlace = 0;
        money = 1500;
    }

    public void changeMoney(int change){
        money -= change;
        base.pmoney[pid].setText("Player " + (pid+1) +"'s Money: " + money);
    }

    public void jailed(){
        tileBPlace = 10;
        jailed = true;
        jc = 3;
    }
    
    public boolean isJailed() {
        return jailed;
    }

    public void unJailed(){
        jailed = false;
        jc = 0;
    }


    public Color getColor(){
        switch(pid){
            case 0: return Color.ORANGE;
            case 1: return Color.BLUE;
            case 2: return Color.GREEN;
            case 3: return Color.WHITE;
            default: return null;
        }
    }

    public int getXOffset(){
        switch(pid){
            case 0: return 5;
            case 1: return 15;
            case 2: return 5;
            case 3: return 15;
            default: return 0;
        }
    }

    public int getYOffset(){
        switch(pid){
            case 0: return 5;
            case 1: return 15;
            case 2: return 5;
            case 3: return 15;
            default: return 0;
        }
    }
}
