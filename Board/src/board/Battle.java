/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
import java.util.Random;
import java.util.ArrayList;

public class Battle {
    static Random gen = new Random();

    public static boolean battle(Unit attacker, Unit defender){
        while(!(attacker.isDead() || defender.isDead())){
if(gen.nextBoolean()){
    attacker.health -= defender.attack;
} else {
    defender.health -= attacker.attack;
}
        }
        if(defender.isDead())
            defender.die();
        else
            attacker.die();
        return defender.isDead();
    }

    public static Unit selectMatch(Unit u, ArrayList<Unit> selection){
        return selection.get(gen.nextInt(selection.size()));
    }

/*
 *if symetric attack and defence, reverse atk and def is 1 - proportion
 *
 *
if(gen.nextBoolean()){
    attacker.health -= defender.attack;
} else {
    defender.health -= attacker.attack;
}
 *
    Grunt[100,7,7,0]  vs  Acolyte[100,5,14,0]   40143/50000     0.80286
 *
    Grunt[50,7,7,0]  vs  Acolyte[100,5,14,0]    7596/50000      0.15192
 *
if(gen.nextBoolean()){
    attacker.health -= ((defender.attack * defender.health) / 100 + 1);
} else {
    defender.health -= ((attacker.attack * attacker.health) / 100 + 1);
 }
 *
    Grunt[100,7,7,0]  vs  Acolyte[100,5,14,0]   39107/50000     0.78214
 *
    Grunt[50,7,7,0]  vs  Acolyte[100,5,14,0]    156/50000       0.00312
 *
if(gen.nextBoolean()){
    attacker.health -= ((defender.attack * defender.health) / 100 + defender.attack);
} else {
    defender.health -= ((attacker.attack * attacker.health) / 100 + attacker.attack);
}
 *
    Grunt[100,7,7,0]  vs  Acolyte[100,5,14,0]   39179/50000     0.78358
 *
    Grunt[50,7,7,0]  vs  Acolyte[100,5,14,0]    6643/50000      0.13286
 *
if(gen.nextBoolean()){
    attacker.health -= defender.attack * 2;
} else {
    defender.health -= attacker.attack * 2;
}
 *
    Grunt[100,7,7,0]  vs  Acolyte[100,5,14,0]   34361/50000     0.68722
 *
    Grunt[50,7,7,0]  vs  Acolyte[100,5,14,0]    9722/50000      0.19444
 *
*/

    public static void main(String[]args){
        int sum = 0;
        int tries = 50000;
        for(int i = 0; i<tries;i++){
            Unit u1 = new Fodder(new NationImp(null,null,NationEnum.Darklands),null);
            Unit u2 = new Fodder(new NationImp(null,null,NationEnum.Templars_of_Work),null);
            u2.health = 50;
            u1.attack = 21;
            u2.attack = 42;
            if(i == 0)System.out.print(u1 + "  vs  " + u2 + "  ");
            u1.attack(u2);
            sum +=(u1.health > u2.health)?1:0;
        }
        System.out.println(sum + "/" + tries + "  " + sum * 1.d / tries);
    }
}
