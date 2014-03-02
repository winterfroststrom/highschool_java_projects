/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alter.alter;

/**
 *
 * @author Sparky
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controller implements KeyListener{
    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) { }

    public void keyReleased(KeyEvent e) {
        switch(model.getState()){
            case Vars.StateStart: keyReleasedStart(e);
                break;
            case Vars.StateFile: keyReleasedFile(e);
                break;
        }
    }
    
    public void keyReleasedStart(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE: model.changeState(Vars.StateFile, 50);
                break;
        }
    }
    
    public void update(){
        model.decrementTransition();
    }
    
    public void keyReleasedFile(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE: 
                break;
        }
    }
}
