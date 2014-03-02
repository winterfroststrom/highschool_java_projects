/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applettemplate;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Random;

public class Game implements Runnable{
    Main top;
    Mousie mousie;
    Kesie kesie;

    int lapseTime = 50;

    public Game(Main top){
        this.top = top;
        mousie = new Mousie(this);
        kesie = new Kesie(this);
    }

    public void init(){
        
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(lapseTime);
                top.repaint();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
