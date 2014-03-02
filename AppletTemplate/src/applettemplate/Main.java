/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applettemplate;

/**
 *
 * @author Scalene
 */
import java.applet.Applet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.geom.Line2D;
//import java.awt.Shape;
//import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Random;

public class Main extends Applet{
    Image buffer;
    Graphics2D burger;

    Rectangle A = new Rectangle(0,0,100,100);

    Game game;
    Display display;

    @Override
    public void init() {
        buffer = createImage(A.width, A.height);
        burger = (Graphics2D) buffer.getGraphics();
        burger.setBackground(Color.BLACK);
        burger.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        game = new Game(this);
        display = new Display(this);

        addMouseListener(game.mousie);
        addMouseMotionListener(game.mousie);
        addKeyListener(game.kesie);

        game.init();
    }

    private void loadImages(){
        /*
         try{

         } catch(IOException e){
            e.printStackTrace();
         }

         */
    }

    @Override
    public void start() {
        Thread th = new Thread(game);
        th.start();
    }

    @Override
    public void paint(Graphics g) {
        display.paint(burger);
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
