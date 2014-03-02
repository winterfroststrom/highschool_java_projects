/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Sparky
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;

public class View {
    public static Image paint(Image buffer, Model t){
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.clearRect(0, 0, Constants.rSize*6, Constants.rSize*6);
        g.setFont(new Font(Font.DIALOG,Font.BOLD,50));
        for(int i = 0;i< Model.size; i++){
            for(int j = 0;j< Model.size; j++){
                g.setColor(Color.BLACK);
                g.draw(new Rectangle(i*Constants.rSize,j*Constants.rSize,Constants.rSize,Constants.rSize));
                g.setColor(Color.red);
                String s = "";
                switch(t.getPlayer(i, j)){
                    case Model.p1: s = "O";
                        break;
                    case Model.p2: s = "X";
                        break;
                }
                g.drawString(s,i*Constants.rSize,Constants.rSize + j*Constants.rSize);
            }
        }
        if(t.hasWin() == Model.p1){
            g.drawString("O-WIN", 3*Constants.rSize,Constants.rSize);
        }else if(t.hasWin() == Model.p2){
            g.drawString("X-WIN", 3*Constants.rSize,Constants.rSize);
        } else if(t.getPossibleMoves().size() < 1){
            g.drawString("N-TIE", 3*Constants.rSize,Constants.rSize);
        }
        return buffer;
    }
}
