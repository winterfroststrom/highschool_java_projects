/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public enum UnitType {
    Fodder(1),
    Settler(0),
    Commander(10);

    final int strength;// for calc

    private UnitType(int strength) {
        this.strength = strength;
    }
}
