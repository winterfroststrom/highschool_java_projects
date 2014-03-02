/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Scalene
 */
public class NewJApplet extends Applet implements MouseListener,MouseMotionListener,Runnable{

    /**
     * Initialization method that will be called after the applet is loaded
     * into the browser.
     */
    List l = new List(null,new Point(140,25));


    public void init() {
        // TODO start asynchronous download of heavy resources
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

    }

    @Override
    public void start() {
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run(){
        while(true){
            //long elapse =System.nanoTime();
            repaint();
            //elapse = System.nanoTime() - elapse;
            //elapse /= (1000*100);
            try{
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 300, 300);
        l.paintPart(g);
    }
    

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        l.pressEvent(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {
        l.releaseEvent();
    }

    public void mouseDragged(MouseEvent e) {
        l.dragEvent(e.getPoint());
    }

    public void mouseMoved(MouseEvent e) {

    }
}
