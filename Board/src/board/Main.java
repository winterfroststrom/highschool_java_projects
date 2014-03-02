/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

import java.awt.Color;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Rectangle;
/**
 *
 * @author Scalene
 */
public class Main extends Applet {
    Image buffer;
    Graphics2D burger;
    final int BW = 17;//board tile width
    final int BH = 17;
    final int HEXLEN = 30;
    
    final Rectangle A = new Rectangle(0,    0,          800,        500);//applet
    final Rectangle G = new Rectangle(0,    0,          A.width,    A.height - 100);//board
    final Rectangle D = new Rectangle(0,    G.height,   A.width,    A.height - G.height);//displays

    final Rectangle T = new Rectangle(0,                            D.y - 100,  150,        100);//tile info space
    final Rectangle L = new Rectangle(0,                            D.y,        150,        100);//unit list
    final Rectangle U = new Rectangle(T.width,                      D.y - 100,  150,        100);//unit info
    final Rectangle M = new Rectangle(L.width,                      D.y,        150,        100);//control
    final Rectangle P = new Rectangle(T.width + U.width,            D.y - 100,  150,        100);//production info
    final Rectangle C = new Rectangle(L.width + M.width,            D.y,        150,        100);//city
    final Rectangle O = new Rectangle(T.width + U.width + P.width,  D.y - 100,  150,        100);//overview
    final Rectangle S = new Rectangle(L.width + M.width + C.width,  D.y,        150,        100);//unit selection
    

    Game game;
    boolean editor = false;//sets map editor mode

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    @Override
    public void init() {
        buffer = createImage(A.width, A.height);
        burger = (Graphics2D) buffer.getGraphics();
        burger.setBackground(Color.BLACK);
        burger.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        game = new Game(this);

        addMouseListener(game.mousie);
        addMouseMotionListener(game.mousie);
        addKeyListener(game.kesie);
        if(editor){
            addMouseListener(game.emousie);
            addKeyListener(game.ekesie);
        }
    }

    @Override
    public void start() {
        Thread th = new Thread(game);
        th.start();
    }

    @Override
    public void paint(Graphics g) {
        burger.clearRect(0, 0, A.width, A.height);
        game.paint(burger);
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}