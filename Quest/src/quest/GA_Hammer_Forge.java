/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class GA_Hammer_Forge extends GrandArea{
    A_FT_1_2_1 a121;
    A_FT_1_3_1 a131;
    A_FT_1_4_1 a141;
    A_FT_1_2_2 a122;
    A_FT_1_3_2 a132;
    A_FT_1_4_2 a142;
    A_FT_1_2_3 a123;
    A_FT_1_3_3 a133;
    A_FT_1_4_3 a143;
    A_FT_1_5_2 a152;
    A_FT_1_6_2 a162;
    A_FT_1_7_2 a172;

    public GA_Hammer_Forge(Game game) {
        super(game);
        preArea = 0x01000;
    }

    @Override
    public void load() {
        a121 = new A_FT_1_2_1(game);
        a131 = new A_FT_1_3_1(game);
        a141 = new A_FT_1_4_1(game);
        a122 = new A_FT_1_2_2(game);
        a132 = new A_FT_1_3_2(game);
        a142 = new A_FT_1_4_2(game);
        a123 = new A_FT_1_2_3(game);
        a133 = new A_FT_1_3_3(game);
        a143 = new A_FT_1_4_3(game);
        a152 = new A_FT_1_5_2(game);
        a162 = new A_FT_1_6_2(game);
        a172 = new A_FT_1_7_2(game);
    }


    @Override
    public Area getAreaById(int id) {
        switch(id){
            case 0x01121: return a121;
            case 0x01131: return a131;
            case 0x01141: return a141;
            case 0x01122: return a122;
            case 0x01132: return a132;
            case 0x01142: return a142;
            case 0x01123: return a123;
            case 0x01133: return a133;
            case 0x01143: return a143;
            case 0x01152: return a152;
            case 0x01162: return a162;
            case 0x01172: return a172;
            default: return null;
        }
    }

    @Override
    public void die() {
        preArea = 0x01000;
        current = a131;
        current.loadArea();
    }

    @Override
    public void setCurrentById(int id) {
        switch(id){
            case 0x00000: current = a131;
                break;
            case 0x00122: current = a131;
                break;
            default: current = null;
                break;
        }
    }
}
