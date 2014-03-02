/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.View;

/**
 *
 * @author Scalene
 */

//import alter.Main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import alter.Main.EventSystem;
import alter.Main.Event;
import java.util.LinkedList;

public class View extends JPanel implements EventSystem{
    Painter painter;
    LinkedList<Event> events;

    public View(){
        painter = new Painter(this);
        events = new LinkedList<Event>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(this.getBounds());
        painter.paint((Graphics2D) g);
    }
    
    public LinkedList<Event> getEvents() {
        return events;
    }

    public void process(LinkedList<Event> queue){
        events.clear();
        painter.process(queue);
        repaint();
    }
}
