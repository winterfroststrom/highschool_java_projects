/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Map;

/**
 *
 * @author Scalene
 */

public abstract class Tile {
    public final String id;
    

    public Tile(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
