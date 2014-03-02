/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Main extends Applet implements Runnable{
    Game game;
    Image buffer;
    Graphics2D gb;
    Image buffer2;
    Graphics2D gb2;

    @Override
    public void init() {
        game = new Game();
        buffer = createImage(game.width,game.height);
        gb = (Graphics2D) buffer.getGraphics();
        buffer2 = createImage(game.WIDTH,game.HEIGHT);
        gb2 = (Graphics2D) buffer2.getGraphics();
        gb2.setBackground(Color.BLACK);
        this.addKeyListener(new Key(game));
        this.addMouseListener(new Mouse(game));
        this.addMouseMotionListener(new Mouse(game));
        this.setBackground(Color.BLACK);
    }
/*
 * More Dialogue
 * Tutorial Level
 * Overworld
 * Easier Mid, Harder Boss
 * 
 */
    @Override
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    public void run() {
        while(true){
            if(game.dChange()){
                buffer = createImage(game.width,game.height);
                gb = (Graphics2D) buffer.getGraphics();
            }
            if(!game.paused && !game.dialogue){
                game.loop();
            }
            repaint();
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        game.paint(gb);
        //gb.setColor(Color.red);
        //gb.drawRect(1, 1, game.width-2, game.height-2);
        gb2.drawImage(buffer, game.centerx, game.centery, this);
        //gb2.setColor(Color.BLUE);
        //gb2.drawRect(0, 0, 600, 500);
        game.paint2(gb2);
        gr.drawImage(buffer2,0,0,this);

        gb.clearRect(0, 0, game.width,game.height);
        gb2.clearRect(0, 0, game.WIDTH, game.HEIGHT);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
