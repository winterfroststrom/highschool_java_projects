/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public class UnitCard extends Card implements Attackable{
    private int health;
    private int attack;
    public final String THEME;
    private boolean canAttack;

    public UnitCard(String name, int health, int attack, Resource cost, String theme){
        super(name,cost);
        this.health = health;
        this.attack = attack;
        this.THEME = theme;
    }

    public boolean isDead(){
        return health < 1;
    }

    public int getHealth(){
        return health;
    }

    public void changeHealth(int change){
        health += change;
    }

    public void resetAttack(){
        canAttack = true;
    }

    public boolean canAttack(){
        return canAttack;
    }

    public void attack(Attackable target){
        canAttack = false;
        target.attacked(this);
    }

    public int getAttack(){
        return attack;
    }

    public void attacked(UnitCard attacker){
        changeHealth(-attacker.getAttack());
    }

    public void changeAttack(int change){
        attack += change;
    }

    @Override
    public String toString(){
        return super.toString() + "&unit["+health+","+attack+","+THEME+"]";
    }

    @Override
    public String cardType(){
        return "unit";
    }
}
