/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neohellianalmagemachina;

/**
 *
 * @author Scalene
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Keysie extends KeyAdapter{
    Main main;

    public Keysie(Main main) {
        this.main = main;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_O){
            main.quality = !main.quality;
        }
        switch(main.gametype){
            case Main.single: single(e.getKeyCode());
                break;
            case Main.two: two(e.getKeyCode());
                break;
            default: start(e.getKeyCode());
                break;
        }
    }

    private void single(int key){
        if(main.logic.winstate != Logic.ongoing){
            switch(key){
                case KeyEvent.VK_P: main.newGame();
                    break;
            }
            return;
        }
        if(main.logic.player != main.computer){
             if(main.logic.getHotSeat()){
                hotseat(key);
                return;
            } else if(main.logic.getPaused()){
                paused(key);
                return;
            }
            action(key);
        }
    }

    private void two(int key){
        if(main.logic.winstate != Logic.ongoing){
            switch(key){
                case KeyEvent.VK_P: main.newGame();
                    break;
            }
            return;
        }
        if(main.logic.getHotSeat()){
            hotseat(key);
            return;
        } else if(main.logic.getPaused()){
            paused(key);
            return;
        }
        action(key);
    }



    private void paused(int key){
        switch(key){
            case KeyEvent.VK_R: main.logic.changePaused();
                break;
            case KeyEvent.VK_UP: main.logic.history.change((main.logic.player + 1)% 2,1);
                break;
            case KeyEvent.VK_RIGHT: main.logic.history.change(main.logic.player,1);
                break;
            case KeyEvent.VK_LEFT: main.logic.history.change(main.logic.player,-1);
                break;
            case KeyEvent.VK_DOWN: main.logic.history.change((main.logic.player + 1)% 2,-1);
                break;
            case KeyEvent.VK_A: main.logic.history.addToMemory(main.logic.player);
                break;
            case KeyEvent.VK_S: main.logic.history.addToMemory((main.logic.player + 1)% 2);
                break;
            case KeyEvent.VK_SPACE: main.logic.history.clearMemory();
                break;
            case KeyEvent.VK_P: main.newGame();
                break;
        }
    }

    private void hotseat(int key){
        switch(key){
            case KeyEvent.VK_R: main.logic.changeHotSeat();
                break;
        }
    }

    private void action(int key){
        switch(key){
            case KeyEvent.VK_A: main.logic.setState(Logic.attack);
                break;
            case KeyEvent.VK_S: main.logic.setState(Logic.move);
                break;
            case KeyEvent.VK_Q: main.logic.setUnit(Logic.wizard);
                break;
            case KeyEvent.VK_W: main.logic.setUnit(Logic.warrior);
                break;
            case KeyEvent.VK_E: main.logic.setUnit(Logic.priest);
                break;
            case KeyEvent.VK_UP: main.logic.moveCursor(-1, 0);
                break;
            case KeyEvent.VK_RIGHT: main.logic.moveCursor(0, 1);
                break;
            case KeyEvent.VK_LEFT: main.logic.moveCursor(0, -1);
                break;
            case KeyEvent.VK_DOWN: main.logic.moveCursor(1, 0);
                break;
            case KeyEvent.VK_D: main.logic.decide();
                break;
            case KeyEvent.VK_R: main.logic.changePaused();
                break;
        }
    }

    private void start(int key){
        switch(key){
            case KeyEvent.VK_A: main.startSingle();
                break;
            case KeyEvent.VK_S: main.startTwo();
                break;
        }
    }
}
