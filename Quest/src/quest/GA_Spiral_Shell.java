/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package quest;

/**
 *
 * @author Scalene
 */
public class GA_Spiral_Shell extends GrandArea{
    A_WT_1_2_1 a121;
    A_WT_1_2_2 a122;
    A_WT_1_2_3 a123;
    A_WT_1_3_3 a133;
    A_WT_1_3_4 a134;
    A_WT_1_4_4 a144;
    A_WT_1_4_3 a143;
    A_WT_1_5_3 a153;
    A_WT_1_5_2 a152;
    A_WT_1_4_2 a142;
    A_WT_1_4_1 a141;
    A_WT_1_3_1 a131;
    A_WT_1_3_2 a132;

    public GA_Spiral_Shell(Game game) {
        super(game);
        preArea = 0x03000;
    }

    @Override
    public void load() {
         a121 = new A_WT_1_2_1(game);
         a122 = new A_WT_1_2_2(game);
         a123 = new A_WT_1_2_3(game);
         a133 = new A_WT_1_3_3(game);
         a134 = new A_WT_1_3_4(game);
         a144 = new A_WT_1_4_4(game);
         a143 = new A_WT_1_4_3(game);
        a153 = new A_WT_1_5_3(game);
        a152 = new A_WT_1_5_2(game);
        a142 = new A_WT_1_4_2(game);
        a141 = new A_WT_1_4_1(game);
        a131 = new A_WT_1_3_1(game);
        a132 = new A_WT_1_3_2(game);
    }


    @Override
    public Area getAreaById(int id) {
        switch(id){
            case 0x03121: return a121;
            case 0x03122: return a122;
            case 0x03123: return a123;
            case 0x03133: return a133;
            case 0x03134: return a134;
            case 0x03144: return a144;
            case 0x03143: return a143;
            case 0x03153: return a153;
            case 0x03152: return a152;
            case 0x03142: return a142;
            case 0x03141: return a141;
            case 0x03131: return a131;
            case 0x03132: return a132;
            default: return null;
        }
    }

    @Override
    public void die() {
        preArea = 0x03000;
        current = a121;
        current.loadArea();
    }

    @Override
    public void setCurrentById(int id) {
        switch(id){
            case 0x00000: current = a121;
                break;
            default: current = null;
                break;
        }
    }
}
