/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */

public class GameBoard {
    private Player[] players;

    public GameBoard(){
        players = new Player[2];
        players[0] = new Player("Player");
        players[1] = new Player("C-01");
    }

    public boolean isVaildPalyer(int player){
        return player > -1 && player < 2;
    }

    public int isLoser(){
        if(players[0].hasLost())
            return 1;
        if(players[1].hasLost())
            return -1;
        return 0;
    }

    public void beginTurn(int player){
        if(players[player].canDraw())
            players[player].draw();
        players[player].resetAttackers();
    }

    public void endTurn(int player){
        players[player].gainResources();
    }
}
