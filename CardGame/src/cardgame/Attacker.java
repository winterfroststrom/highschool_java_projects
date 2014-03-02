/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public interface Attacker {
    public void resetAttack();
    public boolean canAttack();
    public void attack(Attackable target);
}
