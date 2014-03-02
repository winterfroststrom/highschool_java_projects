/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package refactored_game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Sparky
 */
public class Mover extends KeyAdapter {

    public Main base;

    public Mover(Main add) {
        base = add;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int action = e.getKeyCode();
        switch (action) {
            case KeyEvent.VK_UP:
                base.player.ud = base.stay;
                break;
            case KeyEvent.VK_DOWN:
                base.player.ud = base.stay;
                break;
            case KeyEvent.VK_LEFT:
                base.player.lr = base.stay;
                break;
            case KeyEvent.VK_RIGHT:
                base.player.lr = base.stay;
                break;
            case KeyEvent.VK_Q:
                base.player.lShift();
                break;
            case KeyEvent.VK_E:
                base.player.rShift();
                break;
            case KeyEvent.VK_0:
                base.clearAll();
                break;
            case KeyEvent.VK_P:
                base.notPaused = !base.notPaused;
                break;
        }
        /*
         * if(action == KeyEvent.VK_UP) base.player.ud = base.stay; if(action ==
         * KeyEvent.VK_DOWN) base.player.ud = base.stay; if(action ==
         * KeyEvent.VK_LEFT) base.player.lr = base.stay; if(action ==
         * KeyEvent.VK_RIGHT) base.player.lr = base.stay; if(action ==
         * KeyEvent.VK_Q) base.player.lShift(); if(action == KeyEvent.VK_E)
                base.player.rShift();
         */
        //if(action  == KeyEvent.VK_SPACE)
        //base.player.shooting = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int action = e.getKeyCode();
        //System.out.println(action);
        switch (action) {
            case KeyEvent.VK_UP:
                base.player.ud = base.in;
                break;
            case KeyEvent.VK_DOWN:
                base.player.ud = base.out;
                break;
            case KeyEvent.VK_LEFT:
                base.player.lr = base.in;
                break;
            case KeyEvent.VK_RIGHT:
                base.player.lr = base.out;
                break;
        }
        /*
         * if(action == KeyEvent.VK_UP) base.player.ud = base.in; if(action ==
         * KeyEvent.VK_DOWN) base.player.ud = base.out; if(action ==
         * KeyEvent.VK_LEFT) base.player.lr = base.in; if(action ==
         * KeyEvent.VK_RIGHT) base.player.lr = base.out;
         */
        //if(action  == KeyEvent.VK_SPACE)
        //base.player.shooting = true;
    }
}
