/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alter.alter;

/**
 *
 * @author Sparky
 */
public class Model {
    private int prevState;
    private int state;
    private int transition;
    private int maxTransition;
    
    public Model(){
        prevState = Vars.StateNone;
        state = Vars.StateStart;
    }
    
    public int getPrevState(){
        return prevState;
    }
    
    public void changeState(int newState, int transition){
        prevState = state;
        state = newState;
        this.transition = transition;
        this.maxTransition = transition;
    }
    
    public void decrementTransition(){
        if(transition > 0) transition--;
    }
    
    public double getTransitionPercent(){
        return ((double)maxTransition - transition)/maxTransition;
    }
    
    public boolean isTransitioning(){
        return transition > 0;
    }
    
    public int getState(){
        return state;
    }
}
