/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter{
    Game game;
    MKey mKeys;

    public Key(Game game) {
        this.game = game;
        mKeys = new MKey();
    }


    boolean J = false;
    boolean L = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int action = e.getKeyCode();
        if(game.paused)
            keyPressedPaused(action);
        else
            keyPressedMove(action);
    }

    private void keyPressedPaused(int action){
        switch(action){
            case KeyEvent.VK_J:
                break;
            case KeyEvent.VK_K: game.player.attackSet++;
                    if(game.player.attackSet > game.ev.playermaxatk)game.player.attackSet = 0;
                break;
            case KeyEvent.VK_L:
                break;
            case KeyEvent.VK_I: game.player.attackSet--;
                    if(game.player.attackSet < 0)game.player.attackSet = game.ev.playermaxatk;
                break;
            case KeyEvent.VK_P: game.paused = false;
                break;
        }
    }

    private void keyPressedMove(int action){
        switch(action){
            case KeyEvent.VK_W: mKeys.pressed(MKey.W); game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_S: mKeys.pressed(MKey.S); game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_A: mKeys.pressed(MKey.A); game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_D: mKeys.pressed(MKey.D); game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_J: J = true; game.player.tdir = 1;
                break;
            case KeyEvent.VK_K: game.player.sangle += Math.PI;
                break;
            case KeyEvent.VK_L: L = true; game.player.tdir = -1;
                break;
            case KeyEvent.VK_I: game.player.sangle = -Math.atan2(game.player.ydir, game.player.xdir);
                break;
            case KeyEvent.VK_P: game.paused = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int action = e.getKeyCode();
        if(game.paused)
            keyReleasedPaused(action);
        else
            keyReleasedMove(action);
    }

    private void keyReleasedPaused(int action){
        switch(action){
            case KeyEvent.VK_W: mKeys.released(MKey.W);game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_S: mKeys.released(MKey.S);game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_A: mKeys.released(MKey.A);game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_D: mKeys.released(MKey.D);game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_J: J = false; game.player.tdir = (L)?-1:0;
                break;
            case KeyEvent.VK_L: L = false; game.player.tdir = (J)?1:0;
                break;
        }
    }

    private void keyReleasedMove(int action){
        switch(action){
            case KeyEvent.VK_W: mKeys.released(MKey.W);game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_S: mKeys.released(MKey.S);game.player.setYMove(mKeys.getY());
                break;
            case KeyEvent.VK_A: mKeys.released(MKey.A);game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_D: mKeys.released(MKey.D);game.player.setXMove(mKeys.getX());
                break;
            case KeyEvent.VK_J: J = false; game.player.tdir = (L)?-1:0;
                break;
            case KeyEvent.VK_L: L = false; game.player.tdir = (J)?1:0;
                break;
            case KeyEvent.VK_SPACE: game.player.attack = true;
                break;
        }
    }

    private static class MKey{
        int[] keys;
        static final int W = 0;
        static final int A = 1;
        static final int S = 2;
        static final int D = 3;
        int xrecent;
        int yrecent;

        public MKey() {
            keys = new int[4];
        }

        public void pressed(int key){
            keys[key] = 1;
            switch(key){
                case W: yrecent = W;
                    break;
                case A: xrecent = A;
                    break;
                case S: yrecent = S;
                    break;
                case D: xrecent = D;
                    break;
            }
        }

        public void released(int key){
            keys[key] = 0;
            switch(key){
                case W: if(keys[S] == 1)yrecent = S;else yrecent = -1;
                    break;
                case A: if(keys[D] == 1)xrecent = D;else xrecent = -1;
                    break;
                case S: if(keys[W] == 1)yrecent = W;else yrecent = -1;
                    break;
                case D: if(keys[A] == 1)xrecent = A;else xrecent = -1;
                    break;
            }
        }

        public int getY(){
            switch(yrecent){
                case W: return -1;
                case S: return 1;
                default: return 0;
            }
        }

        public int getX(){
            switch(xrecent){
                case A: return -1;
                case D: return 1;
                default: return 0;
            }
        }

        @Override
        public String toString() {
            return "MKey[" + keys[W] + keys[A] + keys[S] + keys[D] + "]";
        }

    }
}
