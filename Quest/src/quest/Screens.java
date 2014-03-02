/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class Screens {
    Game game;

    public Screens(Game game) {
        this.game = game;
    }

    public void paint(Graphics2D g){
        g.setColor(new Color(100,100,255,100));
        String string = "";
        int i = game.player.health();
        while(i > 5){
            string += 5 + " ";
            i -= 5;
        }
        string += i;
        for(i = game.player.hpmax/5 - string.length();i>0;i--){
            string += 0;
        }
        g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        g.drawString(string, 10, 20);
        if(game.dialogue)
            paintDia(g);
        if(game.paused)
            paintPause(g);
    }

    private void paintPause(Graphics2D g){
        g.setColor(new Color(50,50,255,100));
        g.drawRect(50, 50, game.WIDTH-100, game.HEIGHT-100);
        g.fillRect(50, 50, game.WIDTH-100, game.HEIGHT-100);
        g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        g.drawString("Staff", 100, 100);
        g.drawString("Breeze", 100, 150);
        g.drawString("Fire Ball", 100, 200);
        switch(game.player.attackSet){
            case Player.staff: g.drawRect(75, 75, 150, 50);
                break;
            case Player.windb: g.drawRect(75, 125, 150, 50);
                break;
            case Player.fireb: g.drawRect(75, 175, 150, 50);
                break;
        }
    }

    int timer;
    String dialo;

    private void paintDia(Graphics2D g){
        if(--timer < 1 && game.dia.size() >= 1){
            dialo = game.dia.remove(0);
            timer = 40;
        }
        if(game.dia.size() < 1 && timer < 1){
            game.dialogue = false;
            game.dia = null;
            return;
        }
        g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        g.fillRect(80,game.HEIGHT-125,game.WIDTH-160,100);
        g.setColor(Color.WHITE);
        g.drawString(dialo, 100, game.ga.current.height-70);
    }
}
