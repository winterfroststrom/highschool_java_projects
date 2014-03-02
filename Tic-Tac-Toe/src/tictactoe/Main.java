/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoe;

/**
 *
 * @author Scalene
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main extends Applet implements Runnable{
    Mousie mousie = new Mousie();
    Kesie kesie = new Kesie();
    Random ran = new Random();

    static boolean turn;
    static boolean aiturn;
    boolean win;
    static boolean clear;
    static boolean changed;
    static boolean[][] placed;
    static String[][] piece;
    static Rectangle[][] rect;
    static short[] wt;;
    
    @Override
    public void init() {
        this.addMouseListener(mousie);
        this.addKeyListener(kesie);
        load();
    }

    void load(){
        turn = true;
        aiturn = ran.nextBoolean();
        win = false;
        clear = false;
        boolean[][] aplaced = {{false,false,false},{false,false,false},{false,false,false}};
        String[][] apiece = {{"","",""},{"","",""},{"","",""}};
        Rectangle[][] arect = {{new Rectangle(0,0,50,50),new Rectangle(50,0,50,50),new Rectangle(100,0,50,50)},{new Rectangle(0,50,50,50),new Rectangle(50,50,50,50),new Rectangle(100,50,50,50)},{new Rectangle(0,100,50,50),new Rectangle(50,100,50,50),new Rectangle(100,100,50,50)}};
        short[] awt = {0,0,0,0,0,0,0,0,0};
        wt = awt;
        placed = aplaced;
        piece = apiece;
        rect= arect;
    }

    @Override
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        while(true){
            if(!win){
                if(turn == aiturn){
                    playrand();
                }
                if(changed){
                    if(win()){
                        win = true;
                        continue;
                    }
                }
            }
            if(changed)
                repaint();
            changed = false;
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    void playrand(){
        boolean asd = true;
        while(asd){
            int i = ran.nextInt(3);
            int j =ran.nextInt(3);
            if(placed[i][j])
                continue;
            move((turn)?"O":"X",i,j);
            asd = false;
        }
        changed = true;
    }


    boolean win(){
        return match(piece[0][0],piece[0][1],piece[0][2]) ||
                match(piece[2][0],piece[1][1],piece[0][2]) ||
                match(piece[1][0],piece[1][1],piece[1][2]) ||
                match(piece[2][0],piece[2][1],piece[2][2]) ||
                match(piece[0][0],piece[1][0],piece[2][0]) ||
                match(piece[0][1],piece[1][1],piece[2][1]) ||
                match(piece[0][2],piece[1][2],piece[2][2]) ||
                match(piece[0][0],piece[1][1],piece[2][2]);
    }
    
    private boolean match(String a,String b, String c){
        if(a.equals(""))
            return false;
        if(b.equals(""))
            return false;
        if(c.equals(""))
            return false;
        return a.equals(b) && a.equals(c);
    }

    @Override
    public void paint(Graphics g) {
        if(clear){
            g.clearRect(0, 0, 300, 300);
            load();
        }
        Graphics2D gr = (Graphics2D) g;
        gr.setFont(new Font(Font.DIALOG,Font.BOLD,60));
        for(int i = 0;i<3;i++)
            for(int j = 0;j<3;j++){
                gr.setColor(Color.BLACK);
                gr.draw(rect[i][j]);
                gr.setColor(Color.red);
                gr.drawString(piece[i][j], rect[i][j].x + 5, rect[i][j].y + 50);
            }
            if(win){
                gr.drawString("WIN", 10,100);
            }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    static class Kesie extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            changed = true;
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
                clear = true;
        }

    }
    static class Mousie extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            changed = true;
            for(int i = 0;i<3;i++)
                for(int j = 0;j<3;j++){
                    if(rect[i][j].contains(e.getPoint())){
                        if(!placed[i][j]){
                            move((turn)?"O":"X",i,j);
                        }
                        return;
                    }
                }
        }
    }

    static void move(String player,int i, int j){
        piece[i][j] = player;
        turn = !turn;
        placed[i][j] = true;
        wt[i*3 + j] += 3;
    }
}
