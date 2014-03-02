/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scalene
 */
import java.applet.Applet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Template extends Applet implements Runnable{
    private final int PAUSE = 50;
    private Image buffer;

    @Override
    public void init(){
        buffer = createImage(getSize().width, getSize().height);
    }

    @Override
    public void start(){
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run(){
        while(true){

            try{
                Thread.sleep(PAUSE);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)buffer.getGraphics();

        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
}

