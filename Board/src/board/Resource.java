/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public class Resource {
    int hammer;
    int food;
    int wealth;

    public Resource() {
        this.hammer = 0;
        this.food = 0;
        this.wealth = 0;
    }

    public Resource(int hammer, int food, int wealth) {
        this.hammer = hammer;
        this.food = food;
        this.wealth = wealth;
    }

    public void clear(){
        hammer = 0;
        food = 0;
        wealth = 0;
    }
}
