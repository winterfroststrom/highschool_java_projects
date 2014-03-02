/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package board;

/**
 *
 * @author Scalene
 */
public enum UnitEnum {
    //              A       M       H       F       L       T
    Acolyte(        0,      4,      -30,    10,     0,      UnitType.Fodder),
    Grunt(          1,      -3,     20,     -5,     0,      UnitType.Fodder),
    Fodder(         0,      0,      0,      0,      0,      UnitType.Fodder),

    Vicar(          -1,     3,      0,      20,     1,      UnitType.Commander),
    Commander(      0,      0,      0,      0,      0,      UnitType.Commander),

    Settler(        0,      0,      0,      0,      0,      UnitType.Settler);

    static final int BFA = 5;
    static final int BFM = 10;
    static final int BFH = 200;
    static final int BFF = 100;
    static final int BFL = 0;

    static final int BCA = 10;
    static final int BCM = 12;
    static final int BCH = 500;
    static final int BCF = 100;
    static final int BCL = 3;

    static final int BSA = 0;
    static final int BSM = 30;
    static final int BSH = 1000;
    static final int BSF = 200;
    static final int BSL = 0;

    int attack;
    int movement;
    int hammers;
    int food;
    int leadership;
    UnitType type;

    private UnitEnum(int attack, int movement, int hammers, int food, int leadership, UnitType type) {
        this.attack = attack;
        this.movement = movement;
        this.food = food;
        this.hammers = hammers;
        this.leadership = leadership;
        this.type = type;
    }

    public static UnitEnum getByNation(Nation n, UnitType type){
        switch(type){
            case Fodder:
                return getFodder(n);
            case Commander:
                return getCommander(n);
            case Settler:
                return Settler;
        }
        return getFodder(n);
    }

    private static UnitEnum getCommander(Nation n){
        switch(NationEnum.valueOf(n.name)){
            case Templars_of_Work:
                return Vicar;
        }
        return Commander;
    }

    private static UnitEnum getFodder(Nation n){
        switch(NationEnum.valueOf(n.name)){
            case Darklands:
                return Grunt;
            case Templars_of_Work:
                return Acolyte;
        }
        return Fodder;
    }

    public static String display(Nation n,UnitType u){
        UnitEnum ue = getByNation(n,u);
        switch(u){
            case Fodder:
                return ue.name() + "-> A: " + (ue.attack + BFA) +
                        " M:" + (ue.movement + BFM) +
                        " L: " + (ue.leadership + BFL) +
                        " H: " + (ue.hammers + BFH) +
                        " F: " + (ue.food + BFF);
            case Commander:
                return ue.name() + "-> A: " + (ue.attack + BCA) +
                        " M:" + (ue.movement + BCM) +
                        " L: " + (ue.leadership + BCL) +
                        " H: " + (ue.hammers + BCH) +
                        " F: " + (ue.food + BCF);
            case Settler:
                return ue.name() + "-> A: " + (ue.attack + BSA) +
                        " M:" + (ue.movement + BSM) +
                        " L: " + (ue.leadership + BSL) +
                        " H: " + (ue.hammers + BSH) +
                        " F: " + (ue.food + BSF);
        }
        return ue.name() + "-> A: " + (ue.attack + BFA) +
                " M:" + (ue.movement + BFM) +
                " L: " + (ue.leadership + BFL) +
                " H: " + (ue.hammers + BFH) +
                " F: " + (ue.food + BFF);
    }
}
