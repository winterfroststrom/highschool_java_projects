/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neohellianalmagemachina;

/**
 *
 * @author Scalene
 */
import java.applet.Applet;
import java.awt.Graphics;

public class Main extends Applet implements Runnable{
    int gametype;
    public static final int start = 0;
    public static final int single = 1;
    public static final int two = 2;
    int computer = Logic.p2;
    boolean quality;
    Painter painter;
    Keysie keysie;
    Logic logic;

    @Override
    public void init() {
        keysie = new Keysie(this);
        this.addKeyListener(keysie);
        logic = new Logic(this);
        painter = new Painter(this, this.createImage(500, 600),logic);
        quality = true;
        loadImages();
    }

    public void loadImages(){}

    public void newGame(){
        gametype = start;
        painter.clear();
        logic.clear();
    }

    @Override
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    public void run() {
        while(true){
            repaint();
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(painter.paint(),0,0,null);
    }

    public void startSingle(){
        gametype = single;
        logic.startSingle();
    }

    public void startTwo(){
        gametype = two;
        logic.startTwo();
    }

    public void end(){
        gametype = 0;
    }

}
